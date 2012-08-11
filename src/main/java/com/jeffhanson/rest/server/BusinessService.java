package com.jeffhanson.rest.server;

import com.jeffhanson.rest.core.Representation;
import com.jeffhanson.rest.core.Request;

public interface BusinessService {
    public Representation create(Request request)
            throws ServiceExecutionException;

    public Representation read(Request request)
            throws ServiceExecutionException;

    public Representation update(Request request)
            throws ServiceExecutionException;

    public void delete(Request request)
            throws ServiceExecutionException;
}

