package com.service.httpContainer.context;

import com.service.httpContainer.dynamic.HttpDynamic;
import com.service.httpContainer.registration.HttpServletRegistration;
import org.springframework.mock.web.MockServletContext;

import javax.servlet.*;
import javax.servlet.descriptor.JspConfigDescriptor;
import java.util.Collections;
import java.util.EventListener;
import java.util.HashMap;
import java.util.Map;

public class HttpMockServletContext extends MockServletContext {

    private Map<String, String> classNameMap = new HashMap<String, String>();

    private Map<String, Servlet> servletMap = new HashMap<String, Servlet>();

    private Map<String, Class<? extends Servlet>> servletClassMap = new HashMap<String, Class<? extends Servlet>>();

    public Map<String, ServletRegistration> servletRegistrationMap = new HashMap<String, ServletRegistration>();

    public String getVirtualServerName() {
        return null;
    }

    @Override
    public JspConfigDescriptor getJspConfigDescriptor() {
        return null;
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, String className) {
        classNameMap.put(servletName, className);
        HttpServletRegistration httpServletRegistration = new HttpServletRegistration(this);
        servletRegistrationMap.put(servletName, httpServletRegistration);
        return new HttpDynamic(httpServletRegistration);
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, Servlet servlet) {
        servletMap.put(servletName, servlet);
        HttpServletRegistration httpServletRegistration = new HttpServletRegistration(this);
        servletRegistrationMap.put(servletName, httpServletRegistration);
        return new HttpDynamic(httpServletRegistration);
    }

    @Override
    public ServletRegistration.Dynamic addServlet(String servletName, Class<? extends Servlet> servletClass) {
        servletClassMap.put(servletName, servletClass);
        HttpServletRegistration httpServletRegistration = new HttpServletRegistration(this);
        servletRegistrationMap.put(servletName, httpServletRegistration);
        return new HttpDynamic(httpServletRegistration);
    }

    @Override
    public <T extends Servlet> T createServlet(Class<T> c) throws ServletException {
        try {
            return c.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public ServletRegistration getServletRegistration(String servletName) {
        return servletRegistrationMap.get(servletName);
    }

    @Override
    public Map<String, ? extends ServletRegistration> getServletRegistrations() {
        return servletRegistrationMap;
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, String className) {
        return null;
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Filter filter) {
        return null;
    }

    @Override
    public FilterRegistration.Dynamic addFilter(String filterName, Class<? extends Filter> filterClass) {
        return null;
    }

    @Override
    public <T extends Filter> T createFilter(Class<T> c) throws ServletException {
        return null;
    }

    @Override
    public FilterRegistration getFilterRegistration(String filterName) {
        return null;
    }

    @Override
    public Map<String, ? extends FilterRegistration> getFilterRegistrations() {
        return Collections.emptyMap();
    }

    @Override
    public void addListener(Class<? extends EventListener> listenerClass) {
        return;
    }

    @Override
    public void addListener(String className) {
        return;
    }

    @Override
    public <T extends EventListener> void addListener(T t) {
        return;
    }

    @Override
    public <T extends EventListener> T createListener(Class<T> c) throws ServletException {
        return null;
    }
}
