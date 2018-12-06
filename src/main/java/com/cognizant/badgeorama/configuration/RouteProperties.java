package com.cognizant.badgeorama.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@PropertySource("classpath:/route.properties")
@ConfigurationProperties("route")
public class RouteProperties {

    private Map<String, String> endpoint;

    public Map<String, String> getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(Map<String, String> endpoint) {
        this.endpoint = endpoint;
    }

}
