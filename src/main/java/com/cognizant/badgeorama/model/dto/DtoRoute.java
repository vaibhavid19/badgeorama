package com.cognizant.badgeorama.model.dto;

public class DtoRoute {

    private String uiEndpointMethodName;
    private String dtoClass;
    private String restEndpointMethodName;
    private String restEndpoint;

    private DtoRoute() {
    }

    public DtoRoute(String uiEndpointMethodName, String dtoClass, String restEndpointMethodName, String restEndpoint) {
        this.uiEndpointMethodName = uiEndpointMethodName;
        this.dtoClass = dtoClass;
        this.restEndpointMethodName = restEndpointMethodName;
        this.restEndpoint = restEndpoint;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DtoRoute{");
        sb.append("uiEndpointMethodName='").append(uiEndpointMethodName).append('\'');
        sb.append(", dtoClass='").append(dtoClass).append('\'');
        sb.append(", restEndpointMethodName='").append(restEndpointMethodName).append('\'');
        sb.append(", restEndpoint='").append(restEndpoint).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
