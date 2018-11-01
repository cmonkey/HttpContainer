package com.service.httpContainer.registration;

import com.service.httpContainer.context.HttpMockServletContext;

import javax.servlet.ServletRegistration;
import java.util.*;

public class HttpServletRegistration implements ServletRegistration {

    private HttpMockServletContext httpMockServletContext;

    private String runAsRole;

    private Set<String> mapping = new HashSet<>();

    private int loadOnStart;

    public HttpServletRegistration(HttpMockServletContext httpMockServletContext) {
        this.httpMockServletContext = httpMockServletContext;
    }

    public Set<String> addMapping(String... strings) {
        for (String string : strings) {
            mapping.add(string);
        }
        return mapping;
    }

    public Collection<String> getMappings() {
        return mapping;
    }

    public String getRunAsRole() {
        return runAsRole;
    }

    public String getName() {
        return httpMockServletContext.getServletContextName();
    }

    public String getClassName() {
        return this.getName();
    }

    public boolean setInitParameter(String s, String s1) {
        return httpMockServletContext.setInitParameter(s, s1);
    }

    public String getInitParameter(String s) {
        return httpMockServletContext.getInitParameter(s);
    }

    public Set<String> setInitParameters(Map<String, String> map) {
        for (Map.Entry<String, String> entry : map.entrySet()) {
            httpMockServletContext.setInitParameter(entry.getKey(), entry.getValue());
        }
        return map.keySet();
    }

    public Map<String, String> getInitParameters() {
        Map<String, String> map = new HashMap<String, String>();
        Enumeration<String> names = httpMockServletContext.getInitParameterNames();
        while (names.hasMoreElements()) {
            String key = names.nextElement();
            map.put(key, httpMockServletContext.getInitParameter(key));
        }
        return map;
    }

    public void setLoadOnStartup(int i) {
        this.loadOnStart = i;
    }

    public void setRunAsRole(String s) {
        this.setRunAsRole(s);
    }
}