package com.jeffhanson.rest.server.http;

import com.jeffhanson.rest.core.Representation;
import com.jeffhanson.rest.core.Request;
import com.jeffhanson.rest.server.BusinessService;
import com.jeffhanson.rest.server.Context;
import com.jeffhanson.rest.server.ContextRequestException;
import com.jeffhanson.rest.server.ServiceExecutionException;
import com.jeffhanson.rest.server.ServiceLocator;
import com.jeffhanson.rest.server.ServiceLocatorException;

public class HTTPContext
        implements Context {
    private String contextRootPath = "";
    private String contextPath = "";

    public HTTPContext(String contextRootPath, String contextPath) {
        this.contextRootPath = contextRootPath;
        this.contextPath = contextPath;
    }

    public String getContextRootPath() {
        return contextRootPath;
    }

    public String getContextPath() {
        return contextPath;
    }

    protected String uriFromRequest(HTTPRequest httpReq) {
        String reqURI = httpReq.getRequest().getRequestURI();
        if (reqURI.startsWith(contextPath)) {
            reqURI = reqURI.substring(contextPath.length());
        }

        return reqURI;
    }

    protected BusinessService resolveBusinessService(HTTPRequest httpReq)
            throws ContextRequestException {
        String uri = uriFromRequest(httpReq);

        try {
            BusinessService businessService = ServiceLocator.locateService(contextRootPath, contextPath, uri);
            return businessService;
        } catch (ServiceLocatorException e) {
            e.printStackTrace();
            throw new ContextRequestException(e);
        }
    }

    public void handleDelete(Request request)
            throws ContextRequestException {
        BusinessService businessService = resolveBusinessService((HTTPRequest) request);

        try {
            businessService.delete(request);
        } catch (ServiceExecutionException e) {
            e.printStackTrace();
            throw new ContextRequestException(e);
        }
    }

    public Representation handleGet(Request request)
            throws ContextRequestException {
        BusinessService businessService = resolveBusinessService((HTTPRequest) request);
        Representation representation = null;

        try {
            representation = businessService.read(request);
        } catch (ServiceExecutionException e) {
            e.printStackTrace();
            throw new ContextRequestException(e);
        }

        return representation;
    }

    public void handlePost(Request request)
            throws ContextRequestException {
        BusinessService businessService = resolveBusinessService((HTTPRequest) request);

        try {
            businessService.update(request);
        } catch (ServiceExecutionException e) {
            e.printStackTrace();
            throw new ContextRequestException(e);
        }
    }

    public void handlePut(Request request)
            throws ContextRequestException {
        try {
            BusinessService businessService = resolveBusinessService((HTTPRequest) request);
            businessService.update(request);
        } catch (ServiceExecutionException e) {
            e.printStackTrace();
            throw new ContextRequestException(e);
        }
    }
}
