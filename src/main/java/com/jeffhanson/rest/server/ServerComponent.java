package com.jeffhanson.rest.server;

import com.jeffhanson.rest.core.Component;

public abstract class ServerComponent
        implements Component {
    private java.util.HashMap<String, Context> contexts =
            new java.util.HashMap<String, Context>();

    abstract public Context addContext(String contextRootPath, String contextPath);

    public java.util.Iterator<String> getContextPaths() {
        return contexts.keySet().iterator();
    }

    public Context getContext(String contextPath) {
        return contexts.get(contextPath);
    }

    protected java.util.HashMap<String, Context> getContexts() {
        return contexts;
    }
}