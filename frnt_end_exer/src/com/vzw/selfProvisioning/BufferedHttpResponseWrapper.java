package com.vzw.selfProvisioning;


import java.io.PrintWriter;
import java.io.IOException;


import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.servlet.http.HttpServletResponse;

/**
 * A custom response wrapper that captures all output in a buffer.
 * @author Manohar R Nallapu
 */
public class BufferedHttpResponseWrapper extends HttpServletResponseWrapper {
    private BufferedServletOutputStream bufferedServletOut
            = new BufferedServletOutputStream();

    private PrintWriter printWriter = null;
    private ServletOutputStream outputStream = null;

    public BufferedHttpResponseWrapper(HttpServletResponse origResponse) {
        super(origResponse);
    }

    public byte[] getBuffer() {
        return this.bufferedServletOut.getBuffer();
    }

    public PrintWriter getWriter() throws IOException {
        if (this.outputStream != null) {
            throw new IllegalStateException(
                    "The Servlet API forbids calling getWriter() after"
                    + " getOutputStream() has been called");
        }

        if (this.printWriter == null) {
            this.printWriter = new PrintWriter(this.bufferedServletOut);
        }
        return this.printWriter;
    }

    public ServletOutputStream getOutputStream() throws IOException {
        if (this.printWriter != null) {
            throw new IllegalStateException(
                "The Servlet API forbids calling getOutputStream() after"
                + " getWriter() has been called");
        }

        if (this.outputStream == null) {
            this.outputStream = this.bufferedServletOut;
        }
        return this.outputStream;
    }

    // override methods that deal with the response buffer

    public void flushBuffer() throws IOException {
        if (this.outputStream != null) {
            this.outputStream.flush();
        } else if (this.printWriter != null) {
            this.printWriter.flush();
        }
    }

    public int getBufferSize() {
        return this.bufferedServletOut.getBuffer().length;
    }

    public void reset() {
        this.bufferedServletOut.reset();
    }

    public void resetBuffer() {
        this.bufferedServletOut.reset();
    }

    public void setBufferSize(int size) {
        this.bufferedServletOut.setBufferSize(size);
    }
}
