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


    // add new jquery here


});
