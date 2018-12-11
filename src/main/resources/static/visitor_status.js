$(document).ready(function () {

    console.log("DOM is ready");

    (function getTime() {

        $.ajax({
            type: "GET",
            url: "/time",
            timeout: 1000,
            success: function (result) {
                console.log("SUCCESS: " + result);
                $("#time").text(result);
            },
            error: function (result) {
                console.log("ERROR: " + result);
            },
            complete: function () {
                setTimeout(getTime, 1000);
            }
        });
    })();

    (function getWaiting() {

        $.ajax({
            type: "GET",
            url: "/monitor/visitors/waiting",
            timeout: 2000,
            success: function (result) {
                console.log("Successfully queried /monitor/visitors/waiting.");

                let text = "";
                result.forEach(function (element, index) {

                    text += "<div class='w3-container w3-padding-16 w3-card'>";


                    text += "<div class='w3-bar w3-theme'>";

                    text += "<div class='w3-row'>";
                    text += "<div class='w3-half w3-padding'>";

                    text += "<div class='w3-large'>";
                    text += (index + 1);
                    text += ' ';
                    text += element.firstName;
                    text += ' ';
                    text += element.lastName;
                    text += "</div>"; // text

                    text += "<div class='w3-small'>";
                    text += "&nbsp;";
                    text += "<i class='fas fa-phone'></i>";
                    text += "&nbsp;";
                    text += element.phoneNumber;
                    text += "</div>"; // small

                    text += "</div>"; // half

                    text += "<div class='w3-half w3-padding'>";

                    text += "<div class='w3-dropdown-hover w3-right'>";
                    text += "<button class='w3-button w3-padding-16'>";
                    text += "Select Action";
                    text += "<i class='fa fa-caret-down'></i>";
                    text += "</button>";

                    text += "<div class='w3-dropdown-content w3-card-4 w3-bar-block'>";
                    text += "<a href='javascript:void(0)' class='w3-bar-item w3-button'>Edit</a>";
                    text += "<a href='javascript:void(0)' class='w3-bar-item w3-button'>Verify</a>";
                    text += "</div>"; // content
                    text += "</div>"; // dropdown

                    text += "</div>"; // half
                    text += "</div>"; // row

                    text += "</div>"; // bar

                    text += 'Status: ';
                    text += element.status;
                    text += ' Visitor Type: ';
                    text += element.visitorType;

                    text += "</div>"; // container

                    if (index != Object.size(result) - 1) {

                        text += "<div class='w3-padding'>";
                        text += "</div>";

                    }

                });

                $("#waiting").html(text);
            },
            error: function (result) {
                console.log("ERROR: " + result);
            },
            complete: function () {
                setTimeout(getWaiting, 10000);
            }
        });
    })();

    (function getCheckedIn() {

        $.ajax({
            type: "GET",
            url: "/monitor/visitors/checkedin",
            timeout: 2000,
            success: function (result) {
                console.log("Successfully queried /monitor/visitors/checkedin.");

                let text = "";
                result.forEach(function (element, index) {

                    text += "<div class='w3-container w3-padding-16 w3-card'>";


                    text += "<div class='w3-bar w3-theme'>";

                    text += "<div class='w3-row'>";
                    text += "<div class='w3-half w3-padding'>";

                    text += "<div class='w3-large'>";
                    text += (index + 1);
                    text += ' ';
                    text += element.firstName;
                    text += ' ';
                    text += element.lastName;
                    text += "</div>"; // text

                    text += "<div class='w3-small'>";
                    text += "&nbsp;";
                    text += "<i class='fas fa-phone'></i>";
                    text += "&nbsp;";
                    text += element.phoneNumber;
                    text += "</div>"; // small

                    text += "</div>"; // half

                    text += "<div class='w3-half w3-padding'>";

                    text += "<div class='w3-dropdown-hover w3-right'>";
                    text += "<button class='w3-button w3-padding-16'>";
                    text += "Select Action";
                    text += "<i class='fa fa-caret-down'></i>";
                    text += "</button>";

                    text += "<div class='w3-dropdown-content w3-card-4 w3-bar-block'>";
                    text += "<a href='javascript:void(0)' class='w3-bar-item w3-button'>Edit</a>";
                    text += "<a href='javascript:void(0)' class='w3-bar-item w3-button'>CheckOut</a>";
                    text += "</div>"; // content
                    text += "</div>"; // dropdown

                    text += "</div>"; // half
                    text += "</div>"; // row

                    text += "</div>"; // bar

                    text += 'Status: ';
                    text += element.status;
                    text += ' Visitor Type: ';
                    text += element.visitorType;

                    text += "</div>"; // container

                    if (index != Object.size(result) - 1) {

                        text += "<div class='w3-padding'>";
                        text += "</div>";

                    }

                });

                $("#in").html(text);
            },
            error: function (result) {
                console.log("ERROR: " + result);
            },
            complete: function () {
                setTimeout(getCheckedIn, 10000);
            }
        });
    })();

    (function getCheckedOut() {

        $.ajax({
            type: "GET",
            url: "/monitor/visitors/checkedout",
            timeout: 2000,
            success: function (result) {
                console.log("Successfully queried /monitor/visitors/checkedout.");

                let text = "";
                result.forEach(function (element, index) {

                    text += "<div class='w3-container w3-padding-16 w3-card'>";


                    text += "<div class='w3-bar w3-theme'>";

                    text += "<div class='w3-row'>";
                    text += "<div class='w3-half w3-padding'>";

                    text += "<div class='w3-large'>";
                    text += (index + 1);
                    text += ' ';
                    text += element.firstName;
                    text += ' ';
                    text += element.lastName;
                    text += "</div>"; // text

                    text += "<div class='w3-small'>";
                    text += "&nbsp;";
                    text += "<i class='fas fa-phone'></i>";
                    text += "&nbsp;";
                    text += element.phoneNumber;
                    text += "</div>"; // small

                    text += "</div>"; // half

                    text += "<div class='w3-half w3-padding'>";

                    text += "<div class='w3-dropdown-hover w3-right'>";
                    text += "<button class='w3-button w3-padding-16'>";
                    text += "Select Action";
                    text += "<i class='fa fa-caret-down'></i>";
                    text += "</button>";

                    text += "<div class='w3-dropdown-content w3-card-4 w3-bar-block'>";
                    text += "<a href='javascript:void(0)' class='w3-bar-item w3-button'>Edit</a>";
                    text += "</div>"; // content
                    text += "</div>"; // dropdown

                    text += "</div>"; // half
                    text += "</div>"; // row

                    text += "</div>"; // bar

                    text += 'Status: ';
                    text += element.status;
                    text += ' Visitor Type: ';
                    text += element.visitorType;

                    text += "</div>"; // container

                    if (index != Object.size(result) - 1) {

                        text += "<div class='w3-padding'>";
                        text += "</div>";

                    }

                });

                $("#out").html(text);
            },
            error: function (result) {
                console.log("ERROR: " + result);
            },
            complete: function () {
                setTimeout(getCheckedOut, 10000);
            }
        });
    })();

    Object.size = function (obj) {
        let size = 0, key;
        for (key in obj) {
            if (obj.hasOwnProperty(key)) size++;
        }
        return size;
    };

    // add new jquery here


});