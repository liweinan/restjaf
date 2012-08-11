package com.jeffhanson.rest.server;

public class ContextRequestException extends Exception {
    private static final long serialVersionUID = -2024727212416908063L;

    public ContextRequestException(String contextPath) {
        super(contextPath);
    }

    public ContextRequestException(Exception e) {
        super(e);
    }
}
