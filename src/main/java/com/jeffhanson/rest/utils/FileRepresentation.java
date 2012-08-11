package com.jeffhanson.rest.utils;

import com.jeffhanson.rest.core.Representation;

import javax.activation.FileDataSource;
import java.io.File;

public class FileRepresentation extends Representation {
    public FileRepresentation(File file) {
        super(new FileDataSource(file));
    }

    public FileDataSource getDataSource() {
        try {
            return this.getDataSource();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public File getFile() {
        try {
            return this.getDataSource().getFile();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}