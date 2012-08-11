package com.jeffhanson.rest.core;

import com.jeffhanson.rest.server.ServerComponent;

public interface Connector {
    public ServerComponent addServer(int port);
}