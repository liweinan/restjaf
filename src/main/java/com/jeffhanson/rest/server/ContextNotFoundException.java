package com.jeffhanson.rest.server;

public class ContextNotFoundException extends Exception {
    private static final long serialVersionUID = -6010002337519406273L;

    public ContextNotFoundException(String contextPath) {
        super(contextPath);
    }
}
