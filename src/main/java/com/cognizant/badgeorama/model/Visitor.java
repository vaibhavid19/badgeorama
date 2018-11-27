package com.cognizant.badgeorama.model;

import java.util.Date;

public class Visitor {

    private String phoneNumber;
    private String firstName;
    private String lastName;
    private String company;
    private String hostName;
    private String hostPhone;
    private String purposeOfVisit;
    private String checkedInBy;
    private String checkedOutBy;
    private String reasonForDeletion;
    private String badgeNumber;

    private Date registerDate;
    private Date checkedInDate;
    private Date checkedOutDate;

    private Boolean active;
    private VisitStatus status;


    public Visitor() {
    }



    public enum VisitStatus {
        UNVERIFIED, WAITING, IN, OUT
    }


}
