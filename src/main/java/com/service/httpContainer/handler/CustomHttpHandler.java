package com.service.httpContainer.handler;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.OutputStream;

public class CustomHttpHandler implements HttpHandler {
    private final DispatcherServlet dispatcherServlet;

    public CustomHttpHandler(DispatcherServlet dispatcherServlet){
        this.dispatcherServlet = dispatcherServlet;
    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        MockHttpServletRequest request = new MockHttpServletRequest();

        request.setPathInfo(httpExchange.getRequestURI().getPath());
        request.setRequestURI(httpExchange.getRequestURI().toString());

        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest(httpExchange.getRequestMethod(),
                httpExchange.getRequestURI().toString());

        MockHttpServletResponse response = new MockHttpServletResponse();

        try {
            dispatcherServlet.service(request, response);
            response.getHeaderNames().stream().forEach(name -> {
                httpExchange.getResponseHeaders().put(name, response.getHeaders(name));
            });
            byte[] contentByteArray = response.getContentAsByteArray();
            httpExchange.sendResponseHeaders(200, contentByteArray.length);
            OutputStream outputStream = httpExchange.getResponseBody();
            outputStream.write(contentByteArray);
            outputStream.flush();
            outputStream.close();
            httpExchange.close();
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
