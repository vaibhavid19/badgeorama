package com.cognizant.badgeorama.model;

import java.util.Date;

public class VisitorAdmin extends Visitor {

    private String visitorStatusSelectedValue;
    private String visitorTypeSelectedValue;

    public VisitorAdmin() {

    }

    public VisitorAdmin(String visitorStatusSelectedValue, String visitorTypeSelectedValue) {
        this.visitorStatusSelectedValue = visitorStatusSelectedValue;
        this.visitorTypeSelectedValue = visitorTypeSelectedValue;
    }

    public VisitorAdmin(String phoneNumber, String firstName, String lastName, String company, String hostName, String hostPhone, String purposeOfVisit, String checkedInBy, String checkedOutBy, String reasonForDeletion, String badgeNumber, Date registerDate, Date checkedInDate, Date checkedOutDate, Long milliSecondsSinceRegistration, Boolean active, VisitStatus status, VisitorType visitorType, String visitorStatusSelectedValue, String visitorTypeSelectedValue) {
        super(phoneNumber, firstName, lastName, company, hostName, hostPhone, purposeOfVisit, checkedInBy, checkedOutBy, reasonForDeletion, badgeNumber, registerDate, checkedInDate, checkedOutDate, milliSecondsSinceRegistration, active, status, visitorType);
        this.visitorStatusSelectedValue = visitorStatusSelectedValue;
        this.visitorTypeSelectedValue = visitorTypeSelectedValue;
    }

    public String getVisitorStatusSelectedValue() {
        return visitorStatusSelectedValue;
    }

    public void setVisitorStatusSelectedValue(String visitorStatusSelectedValue) {
        this.visitorStatusSelectedValue = visitorStatusSelectedValue;
    }

    public String getVisitorTypeSelectedValue() {
        return visitorTypeSelectedValue;
    }

    public void setVisitorTypeSelectedValue(String visitorTypeSelectedValue) {
        this.visitorTypeSelectedValue = visitorTypeSelectedValue;
    }

}
