package com.jeffhanson.rest.server;

public class ServiceLocatorException extends Exception {
    private static final long serialVersionUID = -4601819875464499951L;

    public ServiceLocatorException(String message) {
        super(message);
    }

    public ServiceLocatorException(Throwable cause) {
        super(cause);
    }

    public ServiceLocatorException(String message, Throwable cause) {
        super(message, cause);
    }

}
