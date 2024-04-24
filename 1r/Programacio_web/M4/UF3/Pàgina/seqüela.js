var foto = document.getElementById("foto");
foto.style.width = "333px";
foto.style.height = "500px";

//-----------------------------

let animacio = document.getElementById("foto");
animacio.style.border = "4px solid";

cicle();
function cicle() {
    const colors = [
        {borderColor: "green"},
        {borderColor: "orange"},
        {borderColor: "blue"},
        {borderColor: "purple"},
        {borderColor: "yellow"},
        {borderColor: "red"},
        {borderColor: "green"}
    ];

    const duracio = {
        duration: 6000,
        iterations: Infinity
    };

    animacio.animate(colors, duracio);
}