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

    private static DtoRoute convertRouteToDtoRoute(Route route) {
        return new DtoRoute(route.uiEndpoint, route.dtoClass, route.restEndpoint);
    }

    public static class Route {

        private String uiEndpoint;
        private String dtoClass;
        private String restEndpoint;

        public String getUiEndpoint() {
            return uiEndpoint;
        }

        public void setUiEndpoint(String uiEndpoint) {
            this.uiEndpoint = uiEndpoint;
        }

        public String getDtoClass() {
            return dtoClass;
        }

        public void setDtoClass(String dtoClass) {
            this.dtoClass = dtoClass;
        }

        public String getRestEndpoint() {
            return restEndpoint;
        }

        public void setRestEndpoint(String restEndpoint) {
            this.restEndpoint = restEndpoint;
        }
    }
}
