"use strict";
let boto = document.getElementById("a");
let numero = document.getElementById("num");
boto.addEventListener('click', (event) => {
    event.preventDefault();
    let num1;
    num1 = Math.floor(Math.random() * (101 - 2) + 2);
    let res = document.getElementById("res");
    if (parseInt(numero.value) >= 1 && parseInt(numero.value) <= num1) {
        res.innerHTML = "El número " + numero.value + " està dins del rang " + 1 + " i " + num1;
    }
    else {
        res.innerHTML = "El número " + numero.value + " NO està dins del rang " + 1 + " i " + num1;
    }
});
