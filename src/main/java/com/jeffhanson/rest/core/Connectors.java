package com.jeffhanson.rest.core;

import com.jeffhanson.rest.server.http.HTTPConnector;

public class Connectors {
    private static java.util.HashMap<String, Connector> instances =
            new java.util.HashMap<String, Connector>();

    public static Connector getConnector(Protocol protocol)
            throws ConnectorException {
        String key = protocol.getScheme();
        Connector instance = instances.get(key);

        if (instance == null) {
            if (key.equalsIgnoreCase(Protocol.HTTP.getScheme())) {
                instance = new HTTPConnector();
                instances.put(key, instance);
            } else {
                throw new ConnectorException("Invalid protocol: " + protocol.getScheme());
            }
        }

        return instance;
    }
}