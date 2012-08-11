package com.jeffhanson.rest.server;

import java.util.HashMap;

public class ServiceLocator {
    private static HashMap<String, BusinessService> services =
            new HashMap<String, BusinessService>();

    public static BusinessService locateService(String contextRootPath,
                                                String contextPath,
                                                String uri)
            throws ServiceLocatorException {
        // TODO locate services mapped to URIs
        //
        BusinessService service = services.get(uri);
        if (service == null) {
            service = new SimpleBusinessService(contextRootPath, contextPath);
            services.put(uri, service);
        }

        return service;
    }
}
