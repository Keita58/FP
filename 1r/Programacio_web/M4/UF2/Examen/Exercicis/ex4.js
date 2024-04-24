$("#btn1").on("click",function (event) {
    $(".main").css("backgroundColor", "hotpink").css("fontStyle", "italic");
})

$("#btn2").on("click",function (event) {
    $(".main > .A").hide();
})

$("#btn3").on("click",function (event) {
    $(".other > .B").text("Light");
})