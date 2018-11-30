package com.cognizant.badgeorama.model.dto;

import com.cognizant.badgeorama.model.Visitor;

public class VisitorLookupSparseDto extends VisitorDto {

    public VisitorLookupSparseDto() {

    }

    public VisitorLookupSparseDto(Visitor visitor) {
        super(visitor);
    }

    public VisitorLookupSparseDto(String phoneNumber) {
        super(phoneNumber);
    }
}
