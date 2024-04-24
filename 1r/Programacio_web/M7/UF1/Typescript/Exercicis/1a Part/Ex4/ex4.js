"use strict";
let boto = document.getElementById("boto");
let frase = document.getElementById("text");
boto.addEventListener("click", (event) => {
    event.preventDefault();
    let array = frase.value.split(" ");
    let res = document.getElementById("res");
    res.innerHTML = "";
    for (let i = array.length - 1; i >= 0; i--) {
        res.innerHTML += array[i] + " ";
    }
});
