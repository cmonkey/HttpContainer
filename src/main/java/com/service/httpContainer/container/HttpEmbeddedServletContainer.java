package com.service.httpContainer.container;

import com.service.httpContainer.handler.CustomHttpHandler;
import com.sun.net.httpserver.HttpServer;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerException;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;
import java.net.InetSocketAddress;

public class HttpEmbeddedServletContainer implements EmbeddedServletContainer {
    private final DispatcherServlet dispatcherServlet;
    private final CustomHttpHandler httpHandler;
    private HttpServer httpServer;

    public HttpEmbeddedServletContainer(DispatcherServlet dispatcherServlet){
        this.dispatcherServlet = dispatcherServlet;
        this.httpHandler = new CustomHttpHandler(dispatcherServlet);
    }
    @Override
    public void start() throws EmbeddedServletContainerException {

        try {
            httpServer = HttpServer.create();
            httpServer.bind(new InetSocketAddress("localhost", getPort()), 0);
            httpServer.createContext("/", httpHandler);
            httpServer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void stop() throws EmbeddedServletContainerException {
        httpServer.stop(1);
    }

    @Override
    public int getPort() {
        return 8080;
    }
}
