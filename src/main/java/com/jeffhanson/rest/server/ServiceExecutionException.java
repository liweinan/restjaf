package com.jeffhanson.rest.server;

public class ServiceExecutionException extends Exception {
    private static final long serialVersionUID = 3201346109446252772L;

    public ServiceExecutionException(String message) {
        super(message);
    }

    public ServiceExecutionException(Throwable cause) {
        super(cause);
    }

    public ServiceExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

}
