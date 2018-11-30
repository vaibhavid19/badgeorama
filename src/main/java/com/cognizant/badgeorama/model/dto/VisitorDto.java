package com.cognizant.badgeorama.model.dto;

import com.cognizant.badgeorama.model.Visitor;
import org.springframework.http.HttpStatus;

public abstract class VisitorDto extends GenericDto {


    public VisitorDto() {
    }

    public VisitorDto(Visitor visitor) {
        super(null, visitor);
    }

    public VisitorDto(String phoneNumber) {
        super(phoneNumber);
    }
}
