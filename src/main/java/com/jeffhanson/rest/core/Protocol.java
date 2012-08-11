package com.jeffhanson.rest.core;

public final class Protocol {
    public static final Protocol HTTP = new Protocol("http");
    public static final Protocol FTP = new Protocol("ftp");
    public static final Protocol SMTP = new Protocol("smtp");

    private String scheme = "";

    public Protocol(String scheme) {
        this.scheme = scheme;
    }

    public String getScheme() {
        return scheme;
    }

    public String toString() {
        return getScheme();
    }
}