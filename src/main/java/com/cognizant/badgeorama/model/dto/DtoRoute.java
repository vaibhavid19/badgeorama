package com.cognizant.badgeorama.model.dto;

public class DtoRoute {

    private String uiEndpoint;
    private String dtoClass;
    private String restEndpoint;

    private DtoRoute() {
    }

    public DtoRoute(String uiEndpoint, String dtoClass, String restEndpoint) {
        this.uiEndpoint = uiEndpoint;
        this.dtoClass = dtoClass;
        this.restEndpoint = restEndpoint;
    }

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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("DtoRoute{");
        sb.append("uiEndpoint='").append(uiEndpoint).append('\'');
        sb.append(", dtoClass='").append(dtoClass).append('\'');
        sb.append(", restEndpoint='").append(restEndpoint).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
