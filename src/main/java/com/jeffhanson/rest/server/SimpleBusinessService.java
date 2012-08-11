package com.jeffhanson.rest.server;

import com.jeffhanson.rest.core.Representation;
import com.jeffhanson.rest.core.Request;
import com.jeffhanson.rest.server.http.HTTPRequest;
import com.jeffhanson.rest.utils.FileRepresentation;

import java.io.*;

// todo: resolve representation using business logic.
// Representation should be appropriate implementation of DataSource
// depending on model generated from business logic.
//
public class SimpleBusinessService
        implements BusinessService {
    // todo: read RESOURCES_DIR from config or system property
    //
    private static final String RESOURCES_DIR = "resources";

    private String contextRootPath = "";
    private String contextPath = "";

    public SimpleBusinessService(String contextRootPath,
                                 String contextPath) {
        this.contextRootPath = contextRootPath;
        this.contextPath = contextPath;
    }

    protected String getContextPath() {
        return contextPath;
    }

    protected String filePathFromRequest(Request httpReq) {
        return contextRootPath + RESOURCES_DIR + ((HTTPRequest) httpReq).getRequest().getPathInfo();
    }

    protected void writeToFile(Request request, File file, boolean append)
            throws IOException,
            FileNotFoundException {
        System.out.println("writeToFile called");

        InputStream inStream = ((HTTPRequest) request).getInputStream();
        byte[] dataBuf = new byte[4096];

        FileOutputStream outStream = new FileOutputStream(file, append);
        int bytesRead = 0;
        while ((bytesRead = inStream.read(dataBuf)) > 0) {
            System.out.println("Writing [" + bytesRead + " bytes]");
            outStream.write(dataBuf, 0, bytesRead);
        }

        outStream.flush();
        outStream.close();
    }

    public Representation create(Request request)
            throws ServiceExecutionException {
        System.out.println("SimpleBusinessService.create()");

        String filePath = filePathFromRequest(request);
        File file = new File(filePath);

        try {
            boolean append = false;
            writeToFile(request, file, append);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceExecutionException(e);
        }

        FileRepresentation representation = new FileRepresentation(file);

        return representation;
    }

    public Representation read(Request request)
            throws ServiceExecutionException {
        System.out.println("SimpleBusinessService.read()");

        String filePath = filePathFromRequest(request);
        File file = new File(filePath);
        if (file.exists() == false) {
            throw new ServiceExecutionException("File [" + filePath + "] does not exist.");
        }

        FileRepresentation representation = new FileRepresentation(file);

        return representation;
    }

    public Representation update(Request request)
            throws ServiceExecutionException {
        System.out.println("SimpleBusinessService.update()");

        String filePath = filePathFromRequest(request);
        File file = new File(filePath);
        FileRepresentation representation = new FileRepresentation(file);

        try {
            boolean append = (file.exists() ? true : false);
            writeToFile(request, file, append);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceExecutionException(e);
        }

        return representation;
    }

    public void delete(Request request)
            throws ServiceExecutionException {
        System.out.println("SimpleBusinessService.delete()");

        String filePath = filePathFromRequest(request);
        System.out.println("SimpleBusinessService.delete() - resolving file ["
                + filePath
                + "]");
        File file = new File(filePath);
        System.out.println("SimpleBusinessService.delete() - file resolved");
        if (file.exists()) {
            System.out.println("SimpleBusinessService.delete() - deleting file");

            if (file.delete() == false) {
                System.out.println("SimpleBusinessService.delete() - file deletion failed");
                throw new ServiceExecutionException("Error deleting file ["
                        + filePath + "]");
            }
            System.out.println("SimpleBusinessService.delete() - file deletion succeeded");
        } else {
            System.out.println("SimpleBusinessService.delete() - file ["
                    + filePath
                    + "] does not exist");
        }
    }
}
