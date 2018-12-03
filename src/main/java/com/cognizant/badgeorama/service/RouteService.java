package com.cognizant.badgeorama.service;

import com.cognizant.badgeorama.configuration.RouteProperties;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RouteService {

    private static HashMap<String, String> endpoints;

    private final RouteProperties routeProperties;

    public RouteService(RouteProperties routeProperties) {
        this.routeProperties = routeProperties;
        endpoints = new HashMap<>();
        endpoints.putAll(this.routeProperties.getEndpoint());
    }

    public static Map<String, String> getRouteTable() {
        return endpoints;
    }


}
