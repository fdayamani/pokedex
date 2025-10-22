package com.dayamani.pokedex.client.http;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

import static io.cucumber.spring.CucumberTestContext.SCOPE_CUCUMBER_GLUE;

@Component
@Scope(SCOPE_CUCUMBER_GLUE)
public class Request {

    private String path;
    private String method;
    private Map<String, String> headers = new HashMap<>();
    private String body;

    public Request() {
        // Default constructor for Spring
    }

    public Request(String path, String method, Map<String, String> headers, String body) {
        this.path = path;
        this.method = method;
        this.headers = headers;
        this.body = body;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public void addHeader(String headerName, String value) {
        this.headers.put(headerName, value);
    }

    public void deleteHeader(String headerName) {
        this.headers.remove(headerName);
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    /**
     * Avoid crosstalk between requests
     */
    public void clear() {
        path = null;
        method = null;
        headers = new HashMap<>();
        body = null;
    }

    @Override
    public String toString() {
        return "Request{" +
                "path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", headers=" + headers +
                ", body='" + body + '\'' +
                '}';
    }
}

