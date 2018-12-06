package com.cognizant.badgeorama.model.dto;

import com.cognizant.badgeorama.model.Visitor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class GenericDto implements ModelDto {

    protected ResponseEntity<Visitor> response;
    protected Visitor visitor;
    protected DtoRoute dtoRoute;
    protected List<Visitor> visitors;

    public GenericDto() {
    }

    @Override
    public ResponseEntity<Visitor> getResponse() {
        return response;
    }

    @Override
    public void setResponse(ResponseEntity<Visitor> response) {
        this.response = response;
    }

    @Override
    public Visitor getVisitor() {
        return visitor;
    }

    @Override
    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }

    @Override
    public DtoRoute getDtoRoute() {
        return dtoRoute;
    }

    @Override
    public void setDtoRoute(DtoRoute dtoRoute) {
        this.dtoRoute = dtoRoute;
    }

    @Override
    public List<Visitor> getVisitors() {
        return this.visitors;
    }

    @Override
    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GenericDto{");
        sb.append("response=").append(response);
        sb.append(", visitor=").append(visitor);
        sb.append(", dtoRoute=").append(dtoRoute);
        sb.append('}');
        return sb.toString();
    }
}
