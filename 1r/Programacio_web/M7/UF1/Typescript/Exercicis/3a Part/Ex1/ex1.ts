import {Animes} from "./classes.js";

let arrayAnimes : Animes[] = [];

let div : HTMLDivElement = document.createElement("div");
let div2 : HTMLDivElement = document.createElement("div");
let formulari : HTMLFormElement = document.createElement("form");
let labelNom : HTMLLabelElement = document.createElement("label");
let inputNom : HTMLInputElement = document.createElement("input");
let labelCreador : HTMLLabelElement = document.createElement("label");
let inputCreador : HTMLInputElement = document.createElement("input");
let labelAny : HTMLLabelElement = document.createElement("label");
let inputAny : HTMLInputElement = document.createElement("input");
let boto1 : HTMLButtonElement = document.createElement("button");
let boto2 : HTMLButtonElement = document.createElement("button");

document.body.appendChild(div);
document.body.appendChild(div2);
div.appendChild(formulari);
formulari.onsubmit = guarda;

labelNom.innerText = "Titol Anime: ";
formulari.appendChild(labelNom);
inputNom.placeholder = "Title Anime";
inputNom.required = true;
formulari.appendChild(inputNom);
formulari.appendChild(document.createElement("br"));
labelCreador.innerText = "Creador: ";
formulari.appendChild(labelCreador);
inputCreador.placeholder = "Creator Anime";
inputCreador.required = true;
formulari.appendChild(inputCreador);
formulari.appendChild(document.createElement("br"));
labelAny.innerText = "Primera Publicació: ";
formulari.appendChild(labelAny);
inputAny.placeholder = "year first publication";
inputAny.required = true;
formulari.appendChild(inputAny);
formulari.appendChild(document.createElement("br"));

boto1.innerText = "Add";
formulari.appendChild(boto1);
boto2.innerText = "Get All";
formulari.appendChild(boto2);

boto2.addEventListener('click', (event) => {
    event.preventDefault();
    missatge();
    let text : HTMLElement = document.createElement("p");

    arrayAnimes.forEach((anime) => {
        text.innerHTML += "El titol de l'anime és: " + anime.titol + ", el creador és " + anime.creador + " i l'any de creació és " + anime.any + "." + '<br>';
    });
    div2.appendChild(text);
});

function guarda() {
    // @ts-ignore
    event.preventDefault();
    let Anime = new Animes(inputNom.value, inputCreador.value, ((inputAny.value) as unknown as number));
    arrayAnimes.push(Anime);
    div2.innerHTML = 'Guardat!';
    setTimeout(missatge, 1500);
}

function missatge() {
    div2.innerHTML = '';
}