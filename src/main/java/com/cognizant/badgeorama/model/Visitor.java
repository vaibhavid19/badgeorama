package com.cognizant.badgeorama.model;

import java.util.Date;

/**
 * Main model object for Badge-O-Rama.  Represents a visitor.
 */
// tag::code[]
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
    private VisitorType visitorType;


    public Visitor() {
    }

    public Visitor(String phoneNumber, String firstName, String lastName, String company, String hostName, String hostPhone, String purposeOfVisit, String checkedInBy, String checkedOutBy, String reasonForDeletion, String badgeNumber, Date registerDate, Date checkedInDate, Date checkedOutDate, Boolean active, VisitStatus status, VisitorType visitorType) {
        this.phoneNumber = phoneNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.company = company;
        this.hostName = hostName;
        this.hostPhone = hostPhone;
        this.purposeOfVisit = purposeOfVisit;
        this.checkedInBy = checkedInBy;
        this.checkedOutBy = checkedOutBy;
        this.reasonForDeletion = reasonForDeletion;
        this.badgeNumber = badgeNumber;
        this.registerDate = registerDate;
        this.checkedInDate = checkedInDate;
        this.checkedOutDate = checkedOutDate;
        this.active = active;
        this.status = status;
        this.visitorType = visitorType;
    }

    public enum VisitStatus {
        UNVERIFIED, WAITING, IN, OUT
    }

    public enum VisitorType {
        GUEST, EMPLOYEE
    }


    /**
     * Builder
     */
    public static class VisitorBuilder {

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
        private Visitor.VisitStatus status;
        private Visitor.VisitorType visitorType;

        public VisitorBuilder phoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public VisitorBuilder firstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public VisitorBuilder lastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public VisitorBuilder company(String company) {
            this.company = company;
            return this;
        }

        public VisitorBuilder hostName(String hostName) {
            this.hostName = hostName;
            return this;
        }

        public VisitorBuilder hostPhone(String hostPhone) {
            this.hostPhone = hostPhone;
            return this;
        }

        public VisitorBuilder purposeOfVisit(String purposeOfVisit) {
            this.purposeOfVisit = purposeOfVisit;
            return this;
        }

        public VisitorBuilder checkedInBy(String checkedInBy) {
            this.checkedInBy = checkedInBy;
            return this;
        }

        public VisitorBuilder checkedOutBy(String checkedOutBy) {
            this.checkedOutBy = checkedOutBy;
            return this;
        }

        public VisitorBuilder reasonForDeletion(String reasonForDeletion) {
            this.reasonForDeletion = reasonForDeletion;
            return this;
        }

        public VisitorBuilder badgeNumber(String badgeNumber) {
            this.badgeNumber = badgeNumber;
            return this;
        }

        public VisitorBuilder registerDate(Date registerDate) {
            this.registerDate = registerDate;
            return this;
        }

        public VisitorBuilder checkedInDate(Date checkedInDate) {
            this.checkedInDate = checkedInDate;
            return this;
        }

        public VisitorBuilder checkedOutDate(Date checkedOutDate) {
            this.checkedOutDate = checkedOutDate;
            return this;
        }

        public VisitorBuilder active(Boolean active) {
            this.active = active;
            return this;
        }

        public VisitorBuilder status(Visitor.VisitStatus status) {
            this.status = status;
            return this;
        }

        public VisitorBuilder visitorType(Visitor.VisitorType visitorType) {
            this.visitorType = visitorType;
            return this;
        }

        public Visitor build() {
            return new Visitor(phoneNumber, firstName, lastName, company, hostName, hostPhone, purposeOfVisit, checkedInBy, checkedOutBy, reasonForDeletion, badgeNumber, registerDate, checkedInDate, checkedOutDate, active, status, visitorType);
        }
    }
}
// end::code[]