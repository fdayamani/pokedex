package com.dayamani.pokedex.config;

public class AppProps {

    private String scheme;
    private String host;
    private int port;
    private String contextPath = "";
    private String wiremockScheme;
    private String wiremockHost;
    private int wiremockPort;

    public String getScheme() {
        return scheme;
    }

    public void setScheme(String scheme) {
        this.scheme = scheme;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getContextPath() {
        return contextPath;
    }

    public void setContextPath(String contextPath) {
        this.contextPath = contextPath;
    }

    public String getWiremockHost() {
        return wiremockHost;
    }

    public void setWiremockHost(String wiremockHost) {
        this.wiremockHost = wiremockHost;
    }

    public int getWiremockPort() {
        return wiremockPort;
    }

    public void setWiremockPort(int wiremockPort) {
        this.wiremockPort = wiremockPort;
    }

    public String getWiremockScheme() {
        return wiremockScheme;
    }

    public void setWiremockScheme(String wiremockScheme) {
        this.wiremockScheme = wiremockScheme;
    }
}

