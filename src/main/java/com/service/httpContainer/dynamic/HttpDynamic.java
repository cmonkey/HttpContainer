package com.service.httpContainer.dynamic;

import com.service.httpContainer.registration.HttpServletRegistration;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;
import javax.servlet.ServletSecurityElement;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class HttpDynamic implements ServletRegistration.Dynamic {

    private HttpServletRegistration httpServletRegistration;

    private MultipartConfigElement multipartConfigElement;

    private boolean asyncSupported;

    public HttpDynamic(HttpServletRegistration httpServletRegistration) {
        this.httpServletRegistration = httpServletRegistration;
    }

    public void setLoadOnStartup(int i) {
        httpServletRegistration.setLoadOnStartup(i);
    }

    public void setMultipartConfig(MultipartConfigElement multipartConfigElement) {
        this.multipartConfigElement = multipartConfigElement;
    }

    public void setRunAsRole(String s) {
        httpServletRegistration.setRunAsRole(s);
    }

    public Set<String> setServletSecurity(ServletSecurityElement servletSecurityElement) {
        return null;
    }

    public void setAsyncSupported(boolean b) {
        this.asyncSupported = b;
    }

    public Set<String> addMapping(String... strings) {
        return httpServletRegistration.addMapping(strings);
    }

    public Collection<String> getMappings() {
        return httpServletRegistration.getMappings();
    }

    public String getRunAsRole() {
        return httpServletRegistration.getRunAsRole();
    }

    public String getName() {
        return httpServletRegistration.getName();
    }

    public String getClassName() {
        return this.getName();
    }

    public boolean setInitParameter(String s, String s1) {
        httpServletRegistration.setInitParameter(s, s1);
        return true;
    }

    public String getInitParameter(String s) {
        return httpServletRegistration.getInitParameter(s);
    }

    public Set<String> setInitParameters(Map<String, String> map) {
        return httpServletRegistration.setInitParameters(map);
    }

    public Map<String, String> getInitParameters() {
        return httpServletRegistration.getInitParameters();
    }
}