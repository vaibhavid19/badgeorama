# Badge-O-Rama

Badge-O-Rama UI - made with a pinch of Spring REST goodness and a cup of ReactJS.

## Getting Started

These are instructions for using wireframes as well as endpoint contracts

### Wireframes

You can download and install a 30-day trial of Balsamiq from

* [Balsamiq](https://balsamiq.com)

Click on Download Desktop App and install the desktop application.  You can then open up the ui-wireframes.bmpr file.

A PDF version of the wireframes is also included.  See ui-wireframes.pdf for details.

### REST Endpoint Contracts

#### User Check-In Endpoints
###### Scenario 1

*Scenario 1:*  Visitor has filled in registration and clicked on Submit button.

*Requirement 1:*  Visitor data needs to be sent to database to be stored.

* **POST** /visitor/register

* @RequestBody --> **Visitor** - data from registration form

* Return --> **Boolean** - true if save is successful

* HTTPStatus --> **200**, if return true

* HTTPStatus --> **412**, if return false

*Acceptance Criteria:*  Visitor data is stored in database.

#### User Check-Out Endpoints
######Scenario 1

*Scenario 1:*  Visitor needs to check out and has entered their phone number and has pressed Lookup.

*Requirement 1:*  Visitor data needs to be retrieved from the database based on phone number and sent to UI.

* **GET** /visitor/lookup/{phoneNumber}

* @PathVariable --> **String** - Visitor phone number (ex. 4807601234)

* Return --> **Visitor** - lookup based on phone number

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **404**, if not successful

*Acceptance Criteria:*  Visitor data is sent back to UI.

######Scenario 2

*Scenario 2:*  Visitor needs to check out and has entered their phone number and has pressed Lookup.  They now click on Check-Out to check-out.

*Requirement 2:*  Visitor data needs to be sent to the database with the updated status.

* **PUT** /visitor/checkout/

* @RequestBody --> **Visitor** - Visitor with updated VisitorStatus.

* Return --> **Boolean** - if status successfully updated.

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **412**, if not successful

*Acceptance Criteria:*  Updated status is saved for Visitor.

#### Host Check-In Endpoints

*Scenario 1:*  Host has entered phone number and pressed Lookup.

*Requirement 1:*  Host data needs to be retrieved, (if present), and returned to the UI.

* **GET** /host/lookup/{phoneNumber}

* @PathVariable --> **String** - Host phone number (ex. 4807601234)

* Return --> **Host** - lookup based on phone number

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **404**, if not successful

*Acceptance Criteria:*  Host data is sent back to UI.
