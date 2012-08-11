package com.jeffhanson.rest.core;

import java.io.IOException;
import java.io.InputStream;

public interface Request {
    public InputStream getInputStream()
            throws IOException;
}