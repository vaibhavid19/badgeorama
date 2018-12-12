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

    (function getVisitors() {

        $.ajax({
            type: "GET",
            url: "/monitor/visitors",
            timeout: 2000,
            success: function (result) {
                console.log("Successfully queried visitors.");

                let text = "";
                result.forEach(function (element, index) {

                    text += "<div class='w3-container w3-padding-16 w3-card cog-radius-10'>";

                    if (element.status === "UNVERIFIED") {
                        text += "<div class='w3-bar w3-yellow cog-radius-10'>";
                    } else if (element.status === "WAITING_TIMEOUT") {
                        text += "<div class='w3-bar w3-red cog-radius-10'>";
                    } else if (element.status === "WAITING") {
                        text += "<div class='w3-bar w3-light-blue cog-radius-10'>";
                    } else if (element.status === "IN") {
                        text += "<div class='w3-bar w3-light-green cog-radius-10'>";
                    } else if (element.status === "OUT") {
                        text += "<div class='w3-bar w3-grey cog-radius-10'>";
                    } else {
                        text += "<div class='w3-bar w3-theme cog-radius-10'>";
                    }

                    text += "<div class='w3-row'>";
                    text += "<div class='w3-half w3-padding'>";

                    text += "<div class='w3-large'>";
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

                    if (element.status !== "UNVERIFIED") {
                        text += "<div class='w3-small'>";
                        text += "&nbsp;";
                        text += "<i class='fas fa-id-badge'></i>";
                        text += "&nbsp;";
                        text += element.badgeNumber;
                        text += "</div>"; // small
                    }

                    text += "</div>"; // half

                    text += "<div class='w3-half w3-padding'>";

                    text += "<div class='w3-dropdown-hover w3-right'>";
                    text += "<button class='w3-button w3-padding-16'>";
                    text += "Select Action";
                    text += "&nbsp;";
                    text += "<i class='fa fa-caret-down'></i>";
                    text += "</button>";

                    text += "<div class='w3-dropdown-content w3-card-4 w3-bar-block'>";

                    text += "<div class='w3-bar-item w3-button cog-edit' data-arg1=\'";
                    text += element.phoneNumber.replace(/\D/g, '');
                    text += "\'>Edit</div>";

                    text += "<div class='w3-bar-item w3-button cog-verify' data-arg1=\'";
                    text += element.phoneNumber.replace(/\D/g, '');
                    text += "\'>Verify</div>";

                    text += "<div class='w3-bar-item w3-button cog-checkin' data-arg1=\'";
                    text += element.phoneNumber.replace(/\D/g, '');
                    text += "\'>Check-In</div>";

                    text += "<div class='w3-bar-item w3-button cog-checkout' data-arg1=\'";
                    text += element.phoneNumber.replace(/\D/g, '');
                    text += "\'>Check-Out</div>";

                    text += "</div>"; // content
                    text += "</div>"; // dropdown

                    text += "</div>"; // half
                    text += "</div>"; // row

                    text += "</div>"; // bar

                    text += "<div class='w3-row'>";
                    text += "<div class='w3-half'>";
                    text += 'Status: ';
                    text += element.status;
                    text += "</div>"; // half
                    text += "<div class='w3-half'>";
                    text += "<div class='w3-right'>";
                    text += 'Type: ';
                    text += element.visitorType;
                    text += "</div>"; // right
                    text += "</div>"; // half
                    text += "</div>"; // row

                    text += "</div>"; // container

                    if (index != Object.size(result) - 1) {

                        text += "<div class='w3-padding'>";
                        text += "</div>";

                    }

                });

                $("#visitors").html(text);
                $("#visitorCount").text("Visitors - (" + Object.size(result) + ")");
            },
            error: function (result) {
                console.log("ERROR: " + result);
            },
            complete: function () {
                setTimeout(getVisitors, 10000);
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

    $(document.body).on("click", ".cog-edit", function() {

        let value = $(this).attr("data-arg1");
        alert("edit: " + value);

    });

    $(document.body).on("click", ".cog-verify", function() {

        let value = $(this).attr("data-arg1");
        alert("verify: " + value);

    });

    $(document.body).on("click", ".cog-checkin", function() {

        let value = $(this).attr("data-arg1");
        alert("checkin: " + value);

    });

    $(document.body).on("click", ".cog-checkout", function() {

        let value = $(this).attr("data-arg1");
        alert("checkout: " + value);

    });

    // add new jquery here


});