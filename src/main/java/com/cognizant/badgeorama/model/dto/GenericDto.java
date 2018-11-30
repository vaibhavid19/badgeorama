package com.cognizant.badgeorama.model.dto;

import com.cognizant.badgeorama.model.Visitor;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

public abstract class GenericDto implements ModelDto, Serializable {

    protected ResponseEntity<Visitor> response;

    protected Visitor visitor;

    public GenericDto() {
        this(null, new Visitor());
    }

    public GenericDto(ResponseEntity<Visitor> response, Visitor visitor) {
        this.response = response;
        this.visitor = visitor;
    }

    public GenericDto(String phoneNumber) {

        if (visitor == null) {
            this.visitor = new Visitor();
        }

        this.visitor.setPhoneNumber(phoneNumber);
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
}
