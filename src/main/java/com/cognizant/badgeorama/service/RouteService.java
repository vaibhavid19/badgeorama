package com.cognizant.badgeorama.service;

import com.cognizant.badgeorama.configuration.RouteProperties;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Uses Spring Environment to pull values from the route.properties file.  Endpoints
 * that start with route.endpoint end up in the route table.
 */
@Service
public class RouteService {

    private static HashMap<String, String> endpoints;

    private final Environment env;

    public RouteService(Environment env) {

        this.env = env;
        endpoints = new HashMap<>();

        Map<String, Object> map = new HashMap();
        for(Iterator it = ((AbstractEnvironment) this.env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof ResourcePropertySource) {
                map.putAll(((ResourcePropertySource) propertySource).getSource());
            }
        }

        // we only want the last part of the environment key as a key in our route table
        for (String key : map.keySet()) {
            if (key.startsWith("route.endpoint")) {

                String[] tokens = key.split("[.]");
                String newKey = tokens[tokens.length -1];

                endpoints.put(newKey, (String) map.get(key));
            }
        }



    }

    public static Map<String, String> getRouteTable() {
        return endpoints;
    }


}
