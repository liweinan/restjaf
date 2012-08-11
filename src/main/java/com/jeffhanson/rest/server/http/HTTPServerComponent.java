package com.jeffhanson.rest.server.http;

import com.jeffhanson.rest.server.Context;
import com.jeffhanson.rest.server.ContextNotFoundException;
import com.jeffhanson.rest.server.ServerComponent;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class HTTPServerComponent extends ServerComponent {
    public static String ContextPathFromRequest(HttpServletRequest request,
                                                HttpServlet servlet) {
        return request.getContextPath() + "/" + servlet.getServletName();
    }

    private int port = 80;

    public HTTPServerComponent(int port) {
        this.port = port;
    }

    public Context addContext(String contextRootPath, String contextPath) {
        Context context = getContexts().get(contextPath);
        if (context == null) {
            context = new HTTPContext(contextRootPath, contextPath);
            getContexts().put(contextPath, context);
        }

        return context;
    }

    public HTTPContext getContext(HttpServletRequest httpReq, HttpServlet servlet)
            throws ContextNotFoundException {
        String contextPath = ContextPathFromRequest(httpReq, servlet);
        HTTPContext context = (HTTPContext) getContexts().get(contextPath);

        if (context == null) {
            throw new ContextNotFoundException(contextPath);
        }

        return context;
    }

    public int getPort() {
        return port;
    }
}
