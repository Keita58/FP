import { Animes } from "./classes.js";
let arrayAnimes = [];
let div = document.createElement("div");
let div2 = document.createElement("div");
let formulari = document.createElement("form");
let labelNom = document.createElement("label");
let inputNom = document.createElement("input");
let labelCreador = document.createElement("label");
let inputCreador = document.createElement("input");
let labelAny = document.createElement("label");
let inputAny = document.createElement("input");
let boto1 = document.createElement("button");
let boto2 = document.createElement("button");
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
    let text = document.createElement("p");
    arrayAnimes.forEach((anime) => {
        text.innerHTML += "El titol de l'anime és: " + anime.titol + ", el creador és " + anime.creador + " i l'any de creació és " + anime.any + "." + '<br>';
    });
    div2.appendChild(text);
});
function guarda() {
    // @ts-ignore
    event.preventDefault();
    let Anime = new Animes(inputNom.value, inputCreador.value, (inputAny.value));
    arrayAnimes.push(Anime);
    div2.innerHTML = 'Guardat!';
    setTimeout(missatge, 1500);
}
function missatge() {
    div2.innerHTML = '';
}
