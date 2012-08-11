package com.jeffhanson.rest.server.http;

import com.jeffhanson.rest.core.Response;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HTTPResponse
        implements Response {
    private HttpServletResponse response = null;

    public HTTPResponse(HttpServletResponse response) {
        this.response = response;
    }

    public void write(String contentType, InputStream inStream)
            throws IOException {
        response.setContentType(contentType);
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
        byte[] buf = new byte[4096];
        while (inStream.read(buf) > 0) {
            outStream.write(buf);
        }
        outStream.flush();
    }
}
