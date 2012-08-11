package com.jeffhanson.rest.server;

import com.jeffhanson.rest.core.Representation;
import com.jeffhanson.rest.core.Request;

public interface Context {
    public String getContextPath();

    public Representation handleGet(Request request)
            throws ContextRequestException;

    public void handlePost(Request request)
            throws ContextRequestException;

    public void handlePut(Request request)
            throws ContextRequestException;

    public void handleDelete(Request request)
            throws ContextRequestException;
}