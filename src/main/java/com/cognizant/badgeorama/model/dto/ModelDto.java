package com.cognizant.badgeorama.model.dto;

import com.cognizant.badgeorama.model.Visitor;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ModelDto {

    public ResponseEntity<Visitor> getResponse();

    public void setResponse(ResponseEntity<Visitor> response);

    public Visitor getVisitor();

    public void setVisitor(Visitor visitor);

    public DtoRoute getDtoRoute();

    public void setDtoRoute(DtoRoute dtoRoute);

    public List<Visitor> getVisitors();

    public void setVisitors(List<Visitor> visitors);

}
