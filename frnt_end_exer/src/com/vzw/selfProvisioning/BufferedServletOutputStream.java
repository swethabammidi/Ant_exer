package com.vzw.selfProvisioning;

import java.io.ByteArrayOutputStream;

import javax.servlet.ServletOutputStream;

/**
 * A custom servlet output stream that stores its data in a buffer,
 * rather than sending it directly to the client.
 *
 * @author Manohar R Nallapu
 */
public class BufferedServletOutputStream extends ServletOutputStream {
    // the actual buffer
    private ByteArrayOutputStream bos = new ByteArrayOutputStream();

    /**
     * @return the contents of the buffer.
     */
    public byte[] getBuffer() {
        return this.bos.toByteArray();
    }

    /**
     * This method must be defined for custom servlet output streams.
     */
    public void write(int data) {
        this.bos.write(data);
    }


    public void reset() {
        this.bos.reset();
    }

    
    public void setBufferSize(int size) {
      
        this.bos = new ByteArrayOutputStream(size);
    }
}
