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

###### User Check-In Endpoints

*Scenario 1:*  Visitor has filled in registration and clicked on Submit button.

*Requirement 1:*  Visitor data needs to be sent to database to be stored.

* **POST** /visitor/register

* @RequestBody --> **Visitor** - data from registration form

* Return --> **Boolean** - true if save is successful

* HTTPStatus --> **200**, if return true

* HTTPStatus --> **500**, if return false

*Acceptance Criteria:*  Visitor data is stored in database.

###### User Check-Out Endpoints

More to come!!!