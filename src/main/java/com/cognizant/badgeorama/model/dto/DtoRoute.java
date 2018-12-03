package com.cognizant.badgeorama.model.dto;

public class DtoRoute {

    private String uiEndpointMethodName;
    private String restEndpointMethodName;
    private String restEndpoint;
    private RouteType routeType;

    public DtoRoute() {
    }

    public DtoRoute(String uiEndpointMethodName, String restEndpointMethodName, String restEndpoint, RouteType routeType) {
        this.uiEndpointMethodName = uiEndpointMethodName;
        this.restEndpointMethodName = restEndpointMethodName;
        this.restEndpoint = restEndpoint;
        this.routeType = routeType;
    }

    public String getUiEndpointMethodName() {
        return uiEndpointMethodName;
    }

    public void setUiEndpointMethodName(String uiEndpointMethodName) {
        this.uiEndpointMethodName = uiEndpointMethodName;
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

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        this.routeType = routeType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DtoRoute{");
        sb.append("uiEndpointMethodName='").append(uiEndpointMethodName).append('\'');
        sb.append(", restEndpointMethodName='").append(restEndpointMethodName).append('\'');
        sb.append(", restEndpoint='").append(restEndpoint).append('\'');
        sb.append(", routeType=").append(routeType);
        sb.append('}');
        return sb.toString();
    }
}
