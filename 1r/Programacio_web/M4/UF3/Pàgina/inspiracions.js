var evangelion = document.getElementById("evangelion");
evangelion.style.width = "333px";
evangelion.style.height = "500px";

var godzilla = document.getElementById("godzilla");
godzilla.style.width = "333px";
godzilla.style.height = "500px";

//-----------------------------------------------

var llarg = evangelion.clientWidth;
var alt = evangelion.clientHeight;
$("#evangelion").on("mouseenter", function (event) {
    $("#evangelion").animate({
        width: llarg*1.15,
        height: alt*1.15
    }, 100, "swing");
}).on("mouseleave", function (event) {
    $("#evangelion").animate({
        width: llarg,
        height: alt
    }, 100, "swing");
});

var llargG = godzilla.clientWidth;
var altG = godzilla.clientHeight;
$("#godzilla").on("mouseenter", function (event) {
    $("#godzilla").animate({
        width: llargG*1.15,
        height: altG*1.15
    }, 100, "swing");
}).on("mouseleave", function (event) {
    $("#godzilla").animate({
        width: llargG,
        height: altG
    }, 100, "swing");
});