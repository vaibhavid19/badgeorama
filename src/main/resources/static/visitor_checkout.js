$(document).ready(function () {

    console.log("DOM is ready");

    $("#lookup").click(function (e) {

        e.preventDefault();
        console.log("lookup checkout started");

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
                    $("#visitorName").text(result.firstName + ' ' + result.lastName);
                    $("#company").text(result.company);

                },
                error: function (result) {
                    console.log("ERROR: " + result);

                }
            });

        } else {
            $("#modal1").show();
        }
    });

    $("#reset").click(function (e) {

        $("#visitorName").text('');
        $("#company").text('');

    });

    function display(data) {
        let json = "JSON Response {"
            + JSON.stringify(data, null, 4) + "}";
        console.log(json);
    }

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

    // add new jquery here


});





