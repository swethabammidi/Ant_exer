/**
*  Copyright (c) 2002 by Phil Hanna
*  All rights reserved.
*  
*  You may study, use, modify, and distribute this
*  software for any purpose provided that this
*  copyright notice appears in all copies.
*  
*  This software is provided without warranty
*  either expressed or implied.
*/
package com.openwave.developer.multipartfilter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

/**
* A servlet response wrapper that buffers its output
* and makes it available to the upstream filter
*/
public class BufferedResponseWrapper
   extends HttpServletResponseWrapper
{
   private ByteArrayOutputStream byteBuffer;
   private CharArrayWriter charBuffer;
   private ServletOutputStream outputStream;
   private PrintWriter writer;
   private ServletContext context;

   /**
   * Creates a new <code>BufferedResponseWrapper</code>
   */
   public BufferedResponseWrapper
      (ServletResponse response, ServletContext context)
   {
      super((HttpServletResponse) response);
      this.context = context;
   }

   /**
   * Writes a message to the servlet log file
   */
   public void log(String message)
   {
      context.log("BufferedResponseWrapper: " + message);
   }

   /**
   * Returns a binary output stream
   */
   public ServletOutputStream getOutputStream()
      throws IOException
   {
      if (writer != null) {
         String errmsg = "getWriter() was already called";
         throw new IllegalStateException(errmsg);
      }

      if (outputStream == null) {

         // Create a subclass of ServletOutputStream
         // that delegates everything to a
         // ByteArrayOutputStream

         byteBuffer = new ByteArrayOutputStream();
         outputStream = new ServletOutputStream() {
            public void write(int c) throws IOException {
               byteBuffer.write(c);
            }
         };
      }

      return outputStream;
   }

   /**
   * Returns a character writer
   */
   public PrintWriter getWriter()
      throws IOException
   {
      if (outputStream != null) {
         String errmsg = "getOutputStream() was already called";
         throw new IllegalStateException(errmsg);
      }

      if (writer == null) {
         charBuffer = new CharArrayWriter();
         writer = new PrintWriter(charBuffer);
      }

      return writer;
   }

   /**
   * Returns the contents of the buffer as a string
   */
   public String getBufferAsString()
      throws IOException
   {
      String buffer = null;

      if (charBuffer != null) {
         writer.flush();
         charBuffer.flush();
         buffer = charBuffer.toString();
      }
      else if (byteBuffer != null) {
         outputStream.flush();
         byteBuffer.flush();
         buffer = byteBuffer.toString();
      }
      else
         buffer = "";

      return buffer;
   }

   /**
   * Returns the contents of the buffer as a byte array
   */
   public byte[] getBufferAsByteArray()
      throws IOException
   {
      byte[] buffer = null;

      if (byteBuffer != null) {
         outputStream.flush();
         byteBuffer.flush();
         buffer = byteBuffer.toByteArray();
      }
      else if (charBuffer != null) {
         writer.flush();
         charBuffer.flush();
         buffer = charBuffer.toString().getBytes();
      }
      else
         buffer = new byte[0];

      return buffer;
   }
}
