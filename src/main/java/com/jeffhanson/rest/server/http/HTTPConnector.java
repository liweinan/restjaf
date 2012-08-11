package com.jeffhanson.rest.server.http;

import com.jeffhanson.rest.core.Connector;
import com.jeffhanson.rest.server.ServerComponent;

public class HTTPConnector
        implements Connector {
    public ServerComponent addServer(int port) {
        return new HTTPServerComponent(port);
    }
}
