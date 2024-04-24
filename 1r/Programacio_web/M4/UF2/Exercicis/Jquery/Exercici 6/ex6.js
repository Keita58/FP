$("#btn1").on("click",function (event) {
    $("#box").animate({
        width: "300px",
        height: "300px"
    }, 1500);
})

$("#btn2").on("click",function (event) {
    $("#box").animate({
        width: "100px",
        height: "100px"
    }, 1500);
})