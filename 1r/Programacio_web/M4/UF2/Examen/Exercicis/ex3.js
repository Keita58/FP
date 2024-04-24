$("#box").on("mouseenter", function (event) {
    $("#box").animate({
        width: "100px",
        height: "100px"
    }, 2000);
});

$("#box").on("mouseleave", function (event) {
    $("#box").animate({
        width: "200px",
        height: "200px"
    }, 2000);
});