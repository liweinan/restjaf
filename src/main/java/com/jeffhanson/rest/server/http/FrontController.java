package com.jeffhanson.rest.server.http;

import com.jeffhanson.rest.core.ConnectorException;
import com.jeffhanson.rest.core.Connectors;
import com.jeffhanson.rest.core.Protocol;
import com.jeffhanson.rest.core.Representation;
import com.jeffhanson.rest.server.ContextNotFoundException;
import com.jeffhanson.rest.server.ContextRequestException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Servlet implementation class for Servlet: FrontController
 */
public class FrontController extends javax.servlet.http.HttpServlet
        implements javax.servlet.Servlet {
    private static final long serialVersionUID = 1850220150861735628L;
    private static final String SC_ATTR = "SERVER_COMPONENT";

    /*
    * (non-Java-doc)
    *
    * @see javax.servlet.http.HttpServlet#HttpServlet()
    */
    public FrontController() {
        super();
    }

    public HTTPServerComponent resolveServerComponent(int port,
                                                      String contextRootPath,
                                                      String contextPath)
            throws ServletException {
        ServletContext servletCtx = getServletConfig().getServletContext();
        HTTPServerComponent serverComponent =
                (HTTPServerComponent) servletCtx.getAttribute(SC_ATTR);

        if (serverComponent == null) {
            try {
                HTTPConnector connector =
                        (HTTPConnector) Connectors.getConnector(Protocol.HTTP);
                serverComponent =
                        (HTTPServerComponent) connector.addServer(port);
                System.out.println("FrontController adding context [" + contextPath
                        + "] with root [" + contextRootPath + "]");
                serverComponent.addContext(contextRootPath, contextPath);
                servletCtx.setAttribute(SC_ATTR, serverComponent);
            } catch (ConnectorException e) {
                e.printStackTrace();
                throw new ServletException(e);
            }
        }

        return serverComponent;
    }

    /*
    * (non-Javadoc)
    *
    * @see javax.servlet.http.HttpServlet#doDelete(javax.servlet.http.HttpServletRequest,
    *      javax.servlet.http.HttpServletResponse)
    */
    protected void doDelete(HttpServletRequest request,
                            HttpServletResponse response)
            throws ServletException,
            IOException {
        System.out.println("FrontController.doDelete() called");

        HTTPServerComponent serverComponent =
                resolveServerComponent(request.getServerPort(),
                        this.getServletContext().getRealPath("/"),
                        HTTPServerComponent.ContextPathFromRequest(request, this));

        try {
            serverComponent.getContext(request, this).handleDelete(new HTTPRequest(request));
        } catch (ContextNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Error: " + e);
        } catch (ContextRequestException e) {
            e.printStackTrace();
            throw new ServletException("Error: " + e);
        }

        response.getWriter().println("Success");
    }

    /*
    * (non-Java-doc)
    *
    * @see javax.servlet.http.HttpServlet#doGet(HttpServletRequest request,
    *      HttpServletResponse response)
    */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException,
            IOException {
        /*
        System.out.println("FrontController.doGet() - request LocalName [" + request.getLocalName() + "]");
        System.out.println("FrontController.doGet() - request PathInfo [" + request.getPathInfo() + "]");
        System.out.println("FrontController.doGet() - request PathTranslated [" + request.getPathTranslated() + "]");
        System.out.println("FrontController.doGet() - request RequestURI [" + request.getRequestURI() + "]");
        System.out.println("FrontController.doGet() - request ServerName [" + request.getServerName() + "]");
        System.out.println("FrontController.doGet() - request ServletPath [" + request.getServletPath() + "]");
        System.out.println("FrontController.doGet() - request RequestURL [" + request.getRequestURL() + "]");
        System.out.println("FrontController.doGet() - servlet ServletName [" + this.getServletName() + "]");
        System.out.println("FrontController.doGet() - ServletConfig ServletName [" + this.getServletConfig().getServletName() + "]");
        System.out.println("FrontController.doGet() - ServletContext RealPath [" + this.getServletContext().getRealPath("/") + "]");
        */

        System.out.println("FrontController.doGet() called");
        System.out.println("HTTPServerComponent.ContextPathFromRequest(request, this): " + HTTPServerComponent.ContextPathFromRequest(request, this));
        HTTPServerComponent serverComponent =
                resolveServerComponent(request.getServerPort(),
                        this.getServletContext().getRealPath("/"),
                        HTTPServerComponent.ContextPathFromRequest(request, this));

        Representation representation = null;

        try {
            representation = serverComponent.getContext(request, this).handleGet(new HTTPRequest(request));
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error: " + e);
        }

        new HTTPResponse(response).write(representation.getContentType(),
                representation.getInputStream());
    }

    /*
    * (non-Java-doc)
    *
    * @see javax.servlet.http.HttpServlet#doPost(HttpServletRequest request,
    *      HttpServletResponse response)
    */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException,
            IOException {
        System.out.println("FrontController.doPost() called");

        HTTPServerComponent serverComponent =
                resolveServerComponent(request.getServerPort(),
                        this.getServletContext().getRealPath("/"),
                        HTTPServerComponent.ContextPathFromRequest(request, this));

        try {
            serverComponent.getContext(request, this).handlePost(new HTTPRequest(request));
        } catch (ContextNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Error: " + e);
        } catch (ContextRequestException e) {
            e.printStackTrace();
            throw new ServletException("Error: " + e);
        }

        response.getWriter().println("Success");
    }

    /*
    * (non-Javadoc)
    *
    * @see javax.servlet.http.HttpServlet#doPut(javax.servlet.http.HttpServletRequest,
    *      javax.servlet.http.HttpServletResponse)
    */
    protected void doPut(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException,
            IOException {
        System.out.println("FrontController.doPut() called");

        HTTPServerComponent serverComponent =
                resolveServerComponent(request.getServerPort(),
                        this.getServletContext().getRealPath("/"),
                        HTTPServerComponent.ContextPathFromRequest(request, this));

        try {
            serverComponent.getContext(request, this).handlePut(new HTTPRequest(request));
        } catch (ContextNotFoundException e) {
            e.printStackTrace();
            throw new ServletException("Error: " + e);
        } catch (ContextRequestException e) {
            e.printStackTrace();
            throw new ServletException("Error: " + e);
        }

        response.getWriter().println("Success");
    }
}