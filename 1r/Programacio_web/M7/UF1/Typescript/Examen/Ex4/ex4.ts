import {Patinet} from "./Patinet.js";

let title : HTMLElement = document.createElement("h1");
let form : HTMLFormElement = document.createElement("form");
let label1 : HTMLLabelElement = document.createElement("label");
let label2 : HTMLLabelElement = document.createElement("label");
let label3 : HTMLLabelElement = document.createElement("label");
let label4 : HTMLLabelElement = document.createElement("label");
let input1 : HTMLInputElement = document.createElement("input");
let input2 : HTMLInputElement = document.createElement("input");
let input4 : HTMLInputElement = document.createElement("input");
let tick : HTMLInputElement = document.createElement("input");
let boto1 : HTMLButtonElement = document.createElement("button");
let boto2 : HTMLButtonElement = document.createElement("button");
let text : HTMLElement = document.createElement("p");

document.body.appendChild(title);
document.body.appendChild(form);
document.body.appendChild(text);
title.innerText = "Dades del Patinet";
form.appendChild(label1);
form.appendChild(document.createElement("br"));
form.appendChild(input1);
form.appendChild(document.createElement("br"));
form.appendChild(label2);
form.appendChild(document.createElement("br"));
form.appendChild(input2);
form.appendChild(document.createElement("br"));
form.appendChild(label3);
form.appendChild(tick);
form.appendChild(document.createElement("br"));
form.appendChild(label4);
form.appendChild(document.createElement("br"));
form.appendChild(input4);
form.appendChild(document.createElement("br"));
form.appendChild(boto1);
form.appendChild(boto2);

label1.innerText = "Nom:";
label2.innerText = "Model: ";
label3.innerText = "Contamina: ";
label4.innerText = "Potència: ";
boto1.innerText = "Guardar";
boto2.innerText = "Mostrar";
tick.type = "checkbox";
input1.required = true;
input2.required = true;
input4.required = true;
text.id = "text";

let a : Patinet;

boto1.addEventListener('click', (event : Event) => {
    event.preventDefault();
    a = new Patinet(input1.value, input2.value, tick, input4.value as unknown as number);
    document.getElementById("text").innerHTML = "Guardat!";
    setTimeout(reset, 1500);
});

boto2.addEventListener('click', (event : Event) : void => {
    event.preventDefault();
    a.toString();
});

function reset() {
    document.getElementById("text").innerHTML = "";
}