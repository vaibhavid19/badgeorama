package com.cognizant.badgeorama.service;

import com.cognizant.badgeorama.configuration.RouteProperties;
import com.cognizant.badgeorama.model.dto.DtoRoute;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RouteService {

    private static Map<String, DtoRoute> routeTable;
    private final RouteProperties routeProperties;

    public RouteService(RouteProperties routeProperties) {
        this.routeProperties = routeProperties;
        initializeRouteTable();
    }

    public static Map<String, DtoRoute> getRouteTable() {
        return routeTable;
    }

    public RouteProperties getRouteProperties() {
        return routeProperties;
    }

    /**
     * Creates a route table based on the list of routes.
     *
     * @return route table
     */
    public void initializeRouteTable() {

        routeTable = new HashMap<>();
        for (DtoRoute route : this.routeProperties.getDtoRoutes()) {
            routeTable.put(route.getUiEndpoint(), route);
        }

    }


}
