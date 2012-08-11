package com.jeffhanson.rest.core;

import java.io.IOException;
import java.io.InputStream;

public interface Response {
    public void write(String contentType, InputStream inStream)
            throws IOException;
}