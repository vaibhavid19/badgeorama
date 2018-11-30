package com.cognizant.badgeorama.model.dto;

import com.cognizant.badgeorama.model.Visitor;
import org.springframework.http.ResponseEntity;

public interface ModelDto {

    public ResponseEntity<Visitor> getResponse();

    public void setResponse(ResponseEntity<Visitor> response);

    public Visitor getVisitor();

    public void setVisitor(Visitor visitor);

}
