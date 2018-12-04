$(document).ready(function () {

    console.log("DOM is ready");


    $("#lookup").click(function (e) {

        e.preventDefault();
        console.log("lookup started");

        let data = {};
        let phone = $("#phoneNumber").val();

        if (phone !== '') {

            data["phoneNumber"] = phone;

            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: "/visitor/lookup",
                data: JSON.stringify(data),
                dataType: 'json',
                timeout: 10000,
                success: function (result) {

                    console.log("SUCCESS: " + result);
                    display(result);
                    $("#firstName").val(result.firstName);
                    $("#lastName").val(result.lastName);
                    $("#company").val(result.company);
                    $("#hostName").val(result.hostName);
                    $("#hostMobilePhone").val(result.hostPhone);
                    $("#purpose").val(result.purposeOfVisit);

                },
                error: function (result) {
                    console.log("ERROR: " + result);

                }
            });
        } else {
            $("#modal1").show();
        }
    });

    function display(data) {
        let json = "JSON Response {"
            + JSON.stringify(data, null, 4) + "}";
        console.log(json);
    }

    // add new jquery here


});

function enableBadgeNumber(checkBox) {

    console.log("checkbox clicked");

    if (checkBox.checked == true) {

        document.getElementById("badgeNumberDiv").hidden = false;
        document.getElementById("badgeNumberDiv").disabled = false;
        document.getElementById("badgeNumber").focus();

    } else {

        document.getElementById("badgeNumberDiv").disabled = true;
        document.getElementById("badgeNumberDiv").hidden = true;

    }

}



