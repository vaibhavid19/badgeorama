package com.cognizant.badgeorama.model;

import java.util.Date;

/**
 * Main model object for Badge-O-Rama.  Represents a visitor.
 */
// tag::code[]
public class Visitor {

    private String phoneNumber;        // populated by user
    private String firstName;          // populated by user
    private String lastName;           // populated by user
    private String company;            // populated by user
    private String hostName;           // populated by user
    private String hostPhone;          // populated by user
    private String purposeOfVisit;     // populated by user
    private String checkedInBy;        // populated by system
    private String checkedOutBy;       // populated by system
    private String reasonForDeletion;  // populated by guard/admin
    private String badgeNumber;        // populated by guard/admin

    private Date registerDate;                   // populated by system (backend)
    private Date checkedInDate;                  // populated by system (backend)
    private Date checkedOutDate;                 // populated by system (backend)
    private Long milliSecondsSinceRegistration;  // populated by system (backend) - difference between Instant.now() and registerDate.getTime()
    // example:  Long milliSecondsSinceRegistration = Instant.now() - registerDate.getTime();

    private Boolean active;            // populated by system/guard/admin
    private VisitStatus status;        // populated by system
    private VisitorType visitorType;   // populated by user

    public Visitor() {

    }

    public Visitor(String phoneNumber, String firstName, String lastName, String company, String hostName, String hostPhone, String purposeOfVisit, String checkedInBy, String checkedOutBy, String reasonForDeletion, String badgeNumber, Date registerDate, Date checkedInDate, Date checkedOutDate, Long milliSecondsSinceRegistration, Boolean active, VisitStatus status, VisitorType visitorType) {
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
        this.milliSecondsSinceRegistration = milliSecondsSinceRegistration;
        this.active = active;
        this.status = status;
        this.visitorType = visitorType;
    }

    public static VisitorBuilder builder() {
        return new VisitorBuilder();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getHostPhone() {
        return hostPhone;
    }

    public void setHostPhone(String hostPhone) {
        this.hostPhone = hostPhone;
    }

    public String getPurposeOfVisit() {
        return purposeOfVisit;
    }

    public void setPurposeOfVisit(String purposeOfVisit) {
        this.purposeOfVisit = purposeOfVisit;
    }

    public String getCheckedInBy() {
        return checkedInBy;
    }

    public void setCheckedInBy(String checkedInBy) {
        this.checkedInBy = checkedInBy;
    }

    public String getCheckedOutBy() {
        return checkedOutBy;
    }

    public void setCheckedOutBy(String checkedOutBy) {
        this.checkedOutBy = checkedOutBy;
    }

    public String getReasonForDeletion() {
        return reasonForDeletion;
    }

    public void setReasonForDeletion(String reasonForDeletion) {
        this.reasonForDeletion = reasonForDeletion;
    }

    public String getBadgeNumber() {
        return badgeNumber;
    }

    public void setBadgeNumber(String badgeNumber) {
        this.badgeNumber = badgeNumber;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public Date getCheckedInDate() {
        return checkedInDate;
    }

    public void setCheckedInDate(Date checkedInDate) {
        this.checkedInDate = checkedInDate;
    }

    public Date getCheckedOutDate() {
        return checkedOutDate;
    }

    public void setCheckedOutDate(Date checkedOutDate) {
        this.checkedOutDate = checkedOutDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public VisitStatus getStatus() {
        return status;
    }

    public void setStatus(VisitStatus status) {
        this.status = status;
    }

    public VisitorType getVisitorType() {
        return visitorType;
    }

    public void setVisitorType(VisitorType visitorType) {
        this.visitorType = visitorType;
    }

    public Long getMilliSecondsSinceRegistration() {
        return milliSecondsSinceRegistration;
    }

    public void setMilliSecondsSinceRegistration(Long milliSecondsSinceRegistration) {
        this.milliSecondsSinceRegistration = milliSecondsSinceRegistration;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Visitor{");
        sb.append("phoneNumber='").append(phoneNumber).append('\'');
        sb.append(", firstName='").append(firstName).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", company='").append(company).append('\'');
        sb.append(", hostName='").append(hostName).append('\'');
        sb.append(", hostPhone='").append(hostPhone).append('\'');
        sb.append(", purposeOfVisit='").append(purposeOfVisit).append('\'');
        sb.append(", checkedInBy='").append(checkedInBy).append('\'');
        sb.append(", checkedOutBy='").append(checkedOutBy).append('\'');
        sb.append(", reasonForDeletion='").append(reasonForDeletion).append('\'');
        sb.append(", badgeNumber='").append(badgeNumber).append('\'');
        sb.append(", registerDate=").append(registerDate);
        sb.append(", checkedInDate=").append(checkedInDate);
        sb.append(", checkedOutDate=").append(checkedOutDate);
        sb.append(", milliSecondsSinceRegistration=").append(milliSecondsSinceRegistration);
        sb.append(", active=").append(active);
        sb.append(", status=").append(status);
        sb.append(", visitorType=").append(visitorType);
        sb.append('}');
        return sb.toString();
    }

    public enum VisitStatus {
        UNVERIFIED, WAITING, WAITING_TIMEOUT, IN, OUT
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
        private Long milliSecondsSinceRegistration;
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

        public VisitorBuilder milliSecondsSinceRegistration(Long milliSecondsSinceRegistration) {
            this.milliSecondsSinceRegistration = milliSecondsSinceRegistration;
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
            return new Visitor(phoneNumber, firstName, lastName, company, hostName, hostPhone, purposeOfVisit, checkedInBy, checkedOutBy, reasonForDeletion, badgeNumber, registerDate, checkedInDate, checkedOutDate, milliSecondsSinceRegistration, active, status, visitorType);
        }
    }
}
// end::code[]