package com.cognizant.badgeorama.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralProperties {

    @Value("${app.location}")
    private String location;

    @Value("${rest.client.protocol}")
    private String protocol;

    @Value("${rest.client.host}")
    private String host;

    @Value("${rest.client.port}")
    private String port;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }
}
