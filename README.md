# Badge-O-Rama

Badge-O-Rama UI - made with a pinch of Spring REST goodness and a cup of ReactJS.

## Getting Started

These are instructions for using wireframes as well as endpoint contracts

### Wireframes

You can download and install a 30-day trial of Balsamiq from

* [Balsamiq](https://balsamiq.com)

Click on Download Desktop App and install the desktop application.  You can then open up the ui-wireframes.bmpr file.

A PDF version of the wireframes is also included.  See ui-wireframes.pdf for details.

### Manual JSON Parsing
JSON can be marshalled/unmarshalled to/from Objects manually using Jackson.  An example of how to implement this using a Visitor object is given below:

```java
package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.Date;

public class JacksonParserPOC {

    public static void main(String[] args) {

        // test data
        Visitor source = Visitor.builder()
                .registerDate(new Date())
                .checkedInDate(new Date())
                .checkedOutDate(new Date())
                .phoneNumber("555-555-5555")
                .build();


        // Object --> JSON
        String json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            json = objectMapper.writeValueAsString(source);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        System.out.println(json);

        // JSON --> Object
        Visitor resource = null;
        try {
            resource = objectMapper.readValue(json, Visitor.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(resource);
    }
}
```

### REST Endpoint Contracts

	Visitor Check-In Endpoints

###### Scenario 1 - Visitor Registration

*Scenario 1:*  Visitor has filled in registration and clicked on Submit button.

*Requirement 1:*  Visitor data needs to be sent to database to be stored.

* **POST** /visitor/register

* @RequestBody --> **Visitor** - data from registration form

* Return --> **Visitor** - if there's an error, just return null or empty Visitor (no fields populated).

* HTTPStatus --> **200**, if return true

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Visitor data is stored in database.

	Visitor Check-Out Endpoints

###### Scenario 1 - Visitor Lookup

*Scenario 1:*  Visitor needs to check out and has entered their phone number and has pressed Lookup.

*Requirement 1:*  Visitor data needs to be retrieved from the database based on phone number and sent to UI.

* **GET** /visitor/lookup/{phoneNumber}

* @PathVariable --> **String** - Visitor phone number (ex. 4807601234) Assume the phone number could contain dashes or parenthesis.  (You'll need to strip non-numeric.)

* Return --> **Visitor** - if there's an error, just return null or empty Visitor (no fields populated).

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Visitor data is sent back to UI.

###### Scenario 2 - Visitor Check-Out

*Scenario 2:*  Visitor needs to check out and has entered their phone number and has pressed Lookup.  They now click on Check-Out to check-out.

*Requirement 2:*  Visitor data needs to be sent to the database with the updated status.

* **PUT** /visitor/checkout

* @RequestBody --> **Visitor** - Visitor with updated VisitorStatus.

* Return --> Nothing

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Updated status is saved for Visitor.

	Host Check-In Endpoints

###### Scenario 1 - Visitor Check-In

*Scenario 1:*  Host has entered name/phone and verified correct visitor is on screen.  They now press Check-In to check in the visitor.

*Requirement 1:*  Updated Visitor data needs to be sent to the database with the updated status, host phone and host name.

* **PUT** /visitor/checkin

* @RequestBody --> **Visitor** - Visitor with updated VisitorStatus and Host information.

* Return --> Nothing

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Updated status and host information is saved for Visitor.

	Guest Status Endpoints

###### Scenario 1 - System Update - Waiting

*Scenario 1:*  System needs to update list of Visitors whos status is *WAITING*, *UNREGISTERED*, and *WAITING_TIMEOUT*.

*Requirement 1:*  Return list of Visitors who's status is WAITING or UNVERIFIED

* **GET** /visitors/waiting

* Nothing sent

* Return --> **List<Visitor>** - list of visitors who's status is WAITING or UNVERIFIED.  If error, return null or empty List.

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Visitor data is sent back to UI.

###### Scenario 2 - System Update - Checked-In

*Scenario 2:*  System needs to update list of Visitors in *Checked-In* status list.

*Requirement 2:*  Return list of Visitors who's status is IN

* **GET** /visitors/checkedin

* Nothing sent

* Return --> **List<Visitor>** - list of visitors who's status is IN.  If error, return null or empty List.

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Visitor data is sent back to UI.

###### Scenario 3 - System Update - Checked-Out

*Scenario 3:*  System needs to update list of Visitors in *Checked-Out* status list.

*Requirement 3:*  Return list of Visitors who's status is OUT

* **GET** /visitors/checkedout

* Nothing sent

* Return --> **List<Visitor>** - list of visitors who's status is OUT.  If error, return null or empty List.

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Visitor data is sent back to UI.

###### Scenario 4 - Guard Check-In

*Scenario 4:*  Guard clicks Guard Check-In to check in visitor.

*Requirement 4:*  Updated Visitor needs to be saved in database.

* **PUT** /visitor/checkin

* @RequestBody --> **Visitor** - Visitor with updated VisitorStatus.

* Return --> Nothing

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Updated Visitor is saved in database.

###### Scenario 5 - Guard Check-Out

*Scenario 5:*  Guard clicks Guard Check-Out to check out visitor.

*Requirement 5:*  Updated Visitor needs to be saved in database.

* **PUT** /visitor/checkout

* @RequestBody --> **Visitor** - Visitor with updated VisitorStatus.

* Return --> Nothing

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Updated Visitor status is saved in database.

	Visitor Verify Endpoints
	
###### Scenario 1 - Finish
 
*Scenario 1:*  Guard clicks Finish to verify visitor.
 
*Requirement 1:*  Updated Visitor needs to be saved in database.

* **PUT** /visitor/verify

* @RequestBody --> **Visitor** - Visitor with updated VisitorStatus.

* Return --> Nothing

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Updated visitor information is saved in database.

###### Scenario 2 - Notify Host

*Secnario 2:*  Guard clicks on Notify to notify Host of visitor.

*Requirement 2:*  Notify Host (using host phone number entered by guard or visitor).

* **PUT** /visitor/notifyhost

* @RequestBody --> **Visitor** - Visitor with updated/original host name/phone.

* Return --> Nothing

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:* Host is notified of presence of visitor in lobby.

    Visitor Information Endpoints

###### Scenario 1 - Get Information

*Scenario 1:*  Guard double-clicks on a record on the guest status screen.  All information about the visitor needs to be displayed.

*Requirement 1:*  Visitor data needs to be retrieved from the database based on phone number and sent to UI.

* **GET** /visit/lookup/{phoneNumber}

* @PathVariable --> **String** - Visitor phone number (ex. 4807601234)

* Return --> **Visitor** - lookup based on phone number - densely populated Visitor object.  If there's an error, just return null or empty Visitor (no fields populated).

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Visitor data is sent back to UI.

    Guest Admin Endpoints

###### Scenario 1 - Update Information

*Scenario 1:*  Guard clicks on Update button after editing Visitor record.

*Requirement 1:*  Updated Visitor data needs to be saved to the database.

* **PUT** /visitor/update

* @RequestBody --> **Visitor** - Updated visitor information.  Can be 1 or more fields updated.

* Return --> Nothing

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Updated Visitor data is successfully saved to database.

    Deletion Confirmation Endpoints

###### Scenario 1 - Delete Visitor

*Scenario 1:*  Guard has navigated to deletion confirmation page to delete a record.  The guard has filled in the reason for deletion and clicks on Yes, Delete!

*Requirement 1:*  Updated Visitor data needs to be saved to the database.

* **DELETE** /visitor/delete

* @RequestBody --> **Visitor** - Visitor to be deleted.

* Return --> **Visitor** - if there's an error, just return null or empty Visitor (no fields populated).

* HTTPStatus --> **200**, if successful

* If error, return proper HTTPStatus for error.

*Acceptance Criteria:*  Deleted Visitor has been *soft* deleted from database.  (Mark **active** field false.)

~ Ray Hedgecock