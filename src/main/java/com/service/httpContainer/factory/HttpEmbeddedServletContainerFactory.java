package com.service.httpContainer.factory;

import com.service.httpContainer.container.HttpEmbeddedServletContainer;
import com.service.httpContainer.context.HttpMockServletContext;
import org.springframework.boot.context.embedded.EmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.mock.web.MockServletConfig;
import org.springframework.web.servlet.DispatcherServlet;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

@Configuration
public class HttpEmbeddedServletContainerFactory implements EmbeddedServletContainerFactory {

    @Resource
    private DispatcherServlet dispatcherServlet;

    public EmbeddedServletContainer getEmbeddedServletContainer(ServletContextInitializer... initializers) {
        ServletContext servletContext = new HttpMockServletContext();
        MockServletConfig mockServletConfig = new MockServletConfig();
        for (ServletContextInitializer initializer : initializers) {
            try {
                initializer.onStartup(servletContext);
                dispatcherServlet.init(mockServletConfig);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        return new HttpEmbeddedServletContainer(dispatcherServlet);
    }
}
