package com.cognizant.badgeorama.model.dto;

import com.cognizant.badgeorama.model.Visitor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public abstract class MonitorDto extends GenericDto {

    public MonitorDto() {
    }

    public MonitorDto(ResponseEntity<Visitor> response, Visitor visitor) {
        super(response, visitor);
    }
}
