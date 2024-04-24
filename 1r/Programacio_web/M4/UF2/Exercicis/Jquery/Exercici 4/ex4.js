$("p").addClass("mevaclasse");
$("h1").addClass("mevaclasse2");

$("p").on("click", function (event) {
    $(this).toggleClass("mevaclasse");
});

$("h1").on("click", function (event) {
    $(this).toggleClass("mevaclasse2");
});