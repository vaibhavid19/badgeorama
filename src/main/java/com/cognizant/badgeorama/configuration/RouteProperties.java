package com.cognizant.badgeorama.configuration;

import com.cognizant.badgeorama.model.dto.DtoRoute;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
@PropertySource("classpath:/route.properties")
@ConfigurationProperties("config")
public class RouteProperties {

    private Map<String, Route> routes;

    private static DtoRoute convertRouteToDtoRoute(Route route) {
        return new DtoRoute(route.uiEndpointMethodName, route.dtoClass, route.restEndpointMethodName, route.restEndpoint);
    }

    public Map<String, Route> getRoutes() {
        return routes;
    }

    public void setRoutes(Map<String, Route> routes) {
        this.routes = routes;
    }

    public List<DtoRoute> getDtoRoutes() {

        List<DtoRoute> dtoRoutes = new ArrayList<>();
        for (String key : routes.keySet()) {
            Route route = routes.get(key);
            DtoRoute dtoRoute = convertRouteToDtoRoute(route);
            dtoRoutes.add(dtoRoute);
        }
        return dtoRoutes;
    }

    public static class Route {

        private String uiEndpointMethodName;
        private String dtoClass;
        private String restEndpointMethodName;
        private String restEndpoint;

        public String getUiEndpointMethodName() {
            return uiEndpointMethodName;
        }

        public void setUiEndpointMethodName(String uiEndpointMethodName) {
            this.uiEndpointMethodName = uiEndpointMethodName;
        }

        public String getDtoClass() {
            return dtoClass;
        }

        public void setDtoClass(String dtoClass) {
            this.dtoClass = dtoClass;
        }

        public String getRestEndpointMethodName() {
            return restEndpointMethodName;
        }

        public void setRestEndpointMethodName(String restEndpointMethodName) {
            this.restEndpointMethodName = restEndpointMethodName;
        }

        public String getRestEndpoint() {
            return restEndpoint;
        }

        public void setRestEndpoint(String restEndpoint) {
            this.restEndpoint = restEndpoint;
        }
    }
}
