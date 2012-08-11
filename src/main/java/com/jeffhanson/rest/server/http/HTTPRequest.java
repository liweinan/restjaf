package com.jeffhanson.rest.server.http;

import com.jeffhanson.rest.core.Request;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;

public class HTTPRequest implements Request {
    private HttpServletRequest httpReq = null;

    public HTTPRequest(HttpServletRequest httpReq) {
        this.httpReq = httpReq;
    }

    public HttpServletRequest getRequest() {
        return httpReq;
    }

    public InputStream getInputStream()
            throws IOException {
        return httpReq.getInputStream();
    }
}
