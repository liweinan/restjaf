package com.jeffhanson.rest.core;

import javax.activation.DataSource;

public abstract class Representation extends javax.activation.DataHandler {
    public Representation(DataSource dataSource) {
        super(dataSource);
    }
}