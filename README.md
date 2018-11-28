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

	User Check-In Endpoints

###### Scenario 1

*Scenario 1:*  Visitor has filled in registration and clicked on Submit button.

*Requirement 1:*  Visitor data needs to be sent to database to be stored.

* **POST** /visitor/register

* @RequestBody --> **Visitor** - data from registration form

* Return --> **Boolean** - true if save is successful

* HTTPStatus --> **200**, if return true

* HTTPStatus --> **412**, if return false

*Acceptance Criteria:*  Visitor data is stored in database.

	User Check-Out Endpoints
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

	Host Check-In Endpoints
######Scenario 1 - Host Lookup

*Scenario 1:*  Host has entered phone number and pressed Lookup.

*Requirement 1:*  Host data needs to be retrieved, (if present), and returned to the UI.

* **GET** /host/lookup/{phoneNumber}

* @PathVariable --> **String** - Host phone number (ex. 4807601234)

* Return --> **Host** - lookup based on phone number

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **404**, if not successful

*Acceptance Criteria:*  Host data is sent back to UI.

######Scenario 2 - New Host

*Scenario 2:*  Host has **entered** name (Host data was not present when looked up)and clicked on Confirm Check-In.

*Requirement 2:*  Visitor data needs to be sent to the database with the updated status.  Host data needs to be added.

######Scenario 2, Endpoint 1 - Visitor Check-In

* **PUT** /visitor/checkin/

* @RequestBody --> **Visitor** - Visitor with updated VisitorStatus and Host information.

* Return --> **Boolean** - if status and host information successfully updated.

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **412**, if not successful

*Acceptance Criteria:*  Updated status and host information is saved for Visitor.

######Scenario 2, Endpoint 2 - Host Creation

* **POST** /host/register

* @RequestBody --> **Host** - New Host.

* Return --> **Boolean** - if host successfully created.

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **412**, if not successful

*Acceptance Criteria:*  New Host saved to database.

######Scenario 3 - Updated Host

*Scenario 3:*  Host has **updated** name (Host data was present, but name was incorrect when looked up)and clicked on Confirm Check-In.

*Requirement 3:*  Visitor data needs to be sent to the database with the updated status.  Host data needs to be updated.

######Scenario 3, Endpoint 1 - Visitor Check-In

* **PUT** /visitor/checkin/

* @RequestBody --> **Visitor** - Visitor with updated VisitorStatus and Host information.

* Return --> **Boolean** - if status and host information successfully updated.

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **412**, if not successful

*Acceptance Criteria:*  Updated status and host information is saved for Visitor.

######Scenario 3, Endpoint 2 - Host Update

* **POST** /host/update

* @RequestBody --> **Host** - Host with updated name.

* Return --> **Boolean** - if host successfully updated.

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **412**, if not successful

*Acceptance Criteria:*  Updated Host saved to database.

	Guest Status Endpoints

######Scenario 1 - System Update - Waiting

*Scenario 1:*  System needs to update list of Visitors in *Waiting* status list.

*Requirement 1:*  Return list of Visitors who's status is WAITING or UNVERIFIED

* **GET** /visitors/waiting

* Nothing sent

* Return --> **List<Visitor>** - list of visitors who's status is WAITING or UNVERIFIED

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **404**, if not successful

*Acceptance Criteria:*  Visitor data is sent back to UI.

######Scenario 2 - System Update - Checked-In

*Scenario 2:*  System needs to update list of Visitors in *Checked-In* status list.

*Requirement 2:*  Return list of Visitors who's status is IN

* **GET** /visitors/checkedin

* Nothing sent

* Return --> **List<Visitor>** - list of visitors who's status is IN

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **404**, if not successful

*Acceptance Criteria:*  Visitor data is sent back to UI.

######Scenario 3 - System Update - Checked-Out

*Scenario 3:*  System needs to update list of Visitors in *Checked-Out* status list.

*Requirement 3:*  Return list of Visitors who's status is OUT

* **GET** /visitors/checkedout

* Nothing sent

* Return --> **List<Visitor>** - list of visitors who's status is OUT

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **404**, if not successful

*Acceptance Criteria:*  Visitor data is sent back to UI.

######Scenario 4 - Guard Check-In

*Scenario 4:*  Guard clicks Guard Check-In to check in visitor.

*Requirement 4:*  Updated Visitor needs to be saved in database.

* **PUT** /visitor/checkin/

* @RequestBody --> **Visitor** - Visitor with updated VisitorStatus.

* Return --> **Boolean** - if status and host information successfully updated.

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **412**, if not successful

*Acceptance Criteria:*  Updated Visitor is saved in database.

######Scenario 5 - Guard Check-Out

*Scenario 5:*  Guard clicks Guard Check-Out to check out visitor.

*Requirement 5:*  Updated Visitor needs to be saved in database.

* **PUT** /visitor/checkout/

* @RequestBody --> **Visitor** - Visitor with updated VisitorStatus.

* Return --> **Boolean** - if status successfully updated.

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **412**, if not successful

*Acceptance Criteria:*  Updated status is saved for Visitor.

	Visitor Verify Endpoints
	
######Scenario 1 - Finish
 
*Scenario 1:*  Guard clicks Finish to verify visitor.
 
*Requirement 1:*  Updated Visitor needs to be saved in database.

* **PUT** /visitor/verify/

* @RequestBody --> **Visitor** - Visitor with updated VisitorStatus and Host information.

* Return --> **Boolean** - if visitor status successfully saved.

* HTTPStatus --> **200**, if successful

* HTTPStatus --> **412**, if not successful

*Acceptance Criteria:*  Updated visitor information is saved in database.
