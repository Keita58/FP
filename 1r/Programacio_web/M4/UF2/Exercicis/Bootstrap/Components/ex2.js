$("input#recipient-name").on("change", function (event) {
    //console.log($("input#recipient-name").val())
    $("h3#text1").text($("input#recipient-name").val());
});