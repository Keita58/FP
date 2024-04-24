import {Pokemon} from "./Pokémon.js";
import {Magic} from "./Magic.js";
import {Coord} from "./Coord.js";

let arrayJugadors : (Pokemon | Magic)[] = [];

let div : HTMLElement = document.createElement("div");
let div2 : HTMLElement = document.createElement("div");
let formulari1 : HTMLFormElement = document.createElement("form");
let formulari2 : HTMLFormElement = document.createElement("form");
let label : HTMLLabelElement = document.createElement("label");
let seleccio : HTMLSelectElement = document.createElement("select");
let foto1 : HTMLImageElement = document.createElement("img");
let foto2 : HTMLImageElement = document.createElement("img");
let text : HTMLElement = document.createElement("p");

foto1.src = "media/ltr-0-the-one-ring.png";
foto1.width = 149;
foto1.height = 208;
foto2.src = "media/Zekrom_(Majestad_de_Dragones_TCG).png";
foto2.width = 149;
foto2.height = 208;
text.id = "text";

document.body.appendChild(div);
document.body.appendChild(div2);
div.appendChild(formulari1);
div.appendChild(formulari2);
div2.appendChild(text);
div2.appendChild(foto1);
div2.appendChild(foto2);
formulari1.appendChild(label);
formulari1.appendChild(seleccio);

let pokemon : HTMLOptionElement = document.createElement("option");
let magic : HTMLOptionElement = document.createElement("option");

formulari1.id = 'joc';
formulari2.id = 'valors';
//formulari2.onsubmit = Enviament;
seleccio.id = 'llista';
seleccio.add(pokemon);
seleccio.add(magic);

label.innerText = 'Joc de Rol: ';
pokemon.text = 'Pokémon TCG';
pokemon.id = 'pokemon';
magic.text = 'Magic The Gathering';
magic.id = "magic";

let canvi : HTMLSelectElement = document.getElementById("llista") as HTMLSelectElement;
personatges();
canvi.addEventListener('change', personatges);

function personatges() {
    formulari2.innerHTML="";
    if (canvi.value == 'Pokémon TCG') {
        //document.getElementById("res").innerHTML = '';
        let nom : HTMLInputElement = document.createElement("input");
        let posX : HTMLInputElement = document.createElement("input");
        let posY : HTMLInputElement = document.createElement("input");
        let historia : HTMLInputElement = document.createElement("input");
        let tipus : HTMLInputElement = document.createElement("input");
        let numAtacs : HTMLInputElement = document.createElement("input");

        formulari2.appendChild(nom);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(historia);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(posX);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(posY);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(tipus);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(numAtacs);

        nom.placeholder = 'Nom';
        nom.id = 'nom';
        historia.placeholder = 'Historia';
        historia.id = 'historia';
        posX.placeholder = 'Posició X';
        posX.id = 'posX';
        posX.required = true;
        posY.placeholder = 'Posició Y';
        posY.id = 'posY';
        posY.required = true;
        tipus.placeholder = 'Tipus del Pokémon';
        tipus.id = 'tipus';
        numAtacs.placeholder = "Número d'atacs";
        numAtacs.id = 'atacs';

        let boto1 : HTMLButtonElement = document.createElement("button");
        let boto2 : HTMLButtonElement = document.createElement("button");
        let boto3 : HTMLButtonElement = document.createElement("button");

        boto1.innerText = "Save";
        boto2.innerText = "Show";
        boto3.innerText = "Moure imatge";

        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(boto3);
        formulari2.appendChild(boto1);
        formulari2.appendChild(boto2);

        boto1.addEventListener('click', (event : Event) => {
            Enviament(event);
        });

        boto2.addEventListener('click', (event) => {
            event.preventDefault();
            formulari2.innerHTML += '<br>';
            arrayJugadors.forEach(item => {
                if(item instanceof Pokemon) {
                    formulari2.innerHTML += 'El nom del joc de rol és Pokémon i la seva història ' + item.historia + '. La seva posició és x: ' + item.posicio.x + ' i y: ' + item.posicio.y + '. El nom del Pokémon és ' + item.nom + ', el seu tipus és: ' + item.tipus + ' i té ' + item.numAtacs + ' atacs.';
                    formulari2.innerHTML += '<br>';
                }
                else {
                    formulari2.innerHTML += 'El nom del joc de rol és Magic The Gathering i la seva història ' + item.historia + '. La seva posició és x: ' + item.posicio.x + ' i y: ' + item.posicio.y + '. El nom del monstre és ' + item.nom + ', el seu tipus és: ' + item.tipus + ', té ' + item.mana + ' de manà i es de l`element ' + item.element + '.';
                    formulari2.innerHTML += '<br>';
                }
            });
        });
        console.log(foto2.getBoundingClientRect());
        console.log(foto1.getBoundingClientRect());
        boto3.addEventListener('click', (event) => {
            event.preventDefault();
            let coord : Coord = new Coord((document.getElementById("posX") as HTMLInputElement).value as unknown as number, (document.getElementById("posY") as HTMLInputElement).value as unknown as number);
            /*if((+foto2.getBoundingClientRect().x+ +coord.x >= foto1.getBoundingClientRect().x && (+foto2.getBoundingClientRect().x+ +coord.x) < foto1.getBoundingClientRect().right) && (+foto2.getBoundingClientRect().y+ +coord.y >= foto1.getBoundingClientRect().y && (+foto2.getBoundingClientRect().y+ +coord.y) < foto1.getBoundingClientRect().bottom)) {
                document.getElementById("text").innerText = "No es pot sobreposar una foto sobre l'altre. Canvia les coordenades o mira si t'has equivocat de carta";
                setTimeout(borra, 4000);
            }
            else*/
                foto2.style.transform = "translate(" + coord.x + "px," + coord.y + "px)";
        });
    }
    else {
        //document.getElementById("res").innerHTML = '';
        let nom : HTMLInputElement = document.createElement("input");
        let posX : HTMLInputElement = document.createElement("input");
        let posY : HTMLInputElement = document.createElement("input");
        let historia : HTMLInputElement = document.createElement("input");
        let tipus : HTMLInputElement = document.createElement("input");
        let mana : HTMLInputElement = document.createElement("input");
        let element : HTMLInputElement = document.createElement("input");

        formulari2.appendChild(nom);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(historia);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(posX);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(posY);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(tipus);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(mana);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(element);

        nom.placeholder = 'Nom';
        nom.id = 'nom';
        historia.placeholder = 'Historia';
        historia.id = 'historia';
        posX.placeholder = 'Posició X';
        posX.id = 'posX';
        posX.required = true;
        posY.placeholder = 'Posició Y';
        posY.id = 'posY';
        posY.required = true;
        tipus.placeholder = 'Tipus de carta';
        tipus.id = 'tipus';
        mana.placeholder = 'Cost de manà';
        mana.id = 'mana';
        element.placeholder = "Tipus d'element";
        element.id = "element";

        let boto1 : HTMLButtonElement = document.createElement("button");
        let boto2 : HTMLButtonElement = document.createElement("button");
        let boto3 : HTMLButtonElement = document.createElement("button");

        boto1.innerText = "Save";
        boto2.innerText = "Show";
        boto3.innerText = "Moure imatge";

        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(boto3);
        formulari2.appendChild(boto1);
        formulari2.appendChild(boto2);

        boto1.addEventListener('click', (event : Event) => {
            Enviament(event);
        });

        boto2.addEventListener('click', (event:Event) => {
            event.preventDefault();
            formulari2.innerHTML += '<br>';
            arrayJugadors.forEach(item => {
                if(item instanceof Pokemon) {
                    formulari2.innerHTML += 'El nom del joc de rol és Pokémon i la seva història ' + item.historia + '. La seva posició és x: ' + item.posicio.x + ' i y: ' + item.posicio.y + '. El nom del Pokémon és ' + item.nom + ', el seu tipus és: ' + item.tipus + ' i té ' + item.numAtacs + ' atacs.';
                    formulari2.innerHTML += '<br>';
                }
                else {
                    formulari2.innerHTML += 'El nom del joc de rol és Magic The Gathering i la seva història ' + item.historia + '. La seva posició és x: ' + item.posicio.x + ' i y: ' + item.posicio.y + '. El nom del monstre és ' + item.nom + ', el seu tipus és: ' + item.tipus + ', té ' + item.mana + ' de manà i es de l`element ' + item.element + '.';
                    formulari2.innerHTML += '<br>';
                }
            });
        });

        boto3.addEventListener('click', (event) => {
            event.preventDefault();
            let coord : Coord = new Coord((document.getElementById("posX") as HTMLInputElement).value as unknown as number, (document.getElementById("posY") as HTMLInputElement).value as unknown as number);
            /*if(((+foto1.getBoundingClientRect().x+ +coord.x <= foto2.getBoundingClientRect().right && (+foto1.getBoundingClientRect().x+ +coord.x) < foto2.getBoundingClientRect().x-149) || ((+foto1.getBoundingClientRect().right+ +coord.x) >= foto2.getBoundingClientRect().x && (+foto1.getBoundingClientRect().right+ +coord.x) < foto2.getBoundingClientRect().right+149))) {
                document.getElementById("text").innerText = "No es pot sobreposar una foto sobre l'altre. Canvia les coordenades o mira si t'has equivocat de carta";
                setTimeout(borra, 4000);
            }
            else*/
                foto1.style.transform = "translate(" + coord.x + "px," + coord.y + "px)";
        });
    }
}

function Enviament(event:Event) {
    event.preventDefault();

    if(canvi.value == 'Pokémon TCG') {
        let nom : HTMLInputElement = document.getElementById('nom') as HTMLInputElement;
        let posX : HTMLInputElement = document.getElementById('posX') as HTMLInputElement;
        let posY : HTMLInputElement = document.getElementById('posY') as HTMLInputElement;
        let historia : HTMLInputElement = document.getElementById('historia') as HTMLInputElement;
        let tipus : HTMLInputElement = document.getElementById('tipus') as HTMLInputElement;
        let numAtacs : HTMLInputElement = document.getElementById('atacs') as HTMLInputElement;
        let coord : Coord = new Coord(posX.value as unknown as number, posY.value as unknown as number);
        let aux = new Pokemon(nom.value, historia.value, coord, tipus.value, numAtacs.value as unknown as number);
        arrayJugadors.push(aux);
    }
    else {
        let nom : HTMLInputElement = document.getElementById("nom") as HTMLInputElement;
        let posX : HTMLInputElement = document.getElementById('posX') as HTMLInputElement;
        let posY : HTMLInputElement = document.getElementById('posY') as HTMLInputElement;
        let historia : HTMLInputElement = document.getElementById('historia') as HTMLInputElement;
        let tipus : HTMLInputElement = document.getElementById('tipus') as HTMLInputElement;
        let mana : HTMLInputElement = document.getElementById("mana") as HTMLInputElement;
        let element : HTMLInputElement = document.getElementById("element") as HTMLInputElement;
        let coord : Coord = new Coord(posX.value as unknown as number, posY.value as unknown as number);
        let aux = new Magic(nom.value, historia.value, coord, tipus.value, mana.value as unknown as number, element.value);
        arrayJugadors.push(aux);
    }

    text.innerHTML += '<br>';
    text.innerHTML += "Guardat!!";
    text.innerHTML += '<br>';
    text.innerHTML += "Espera 1s...";
    setTimeout(borra, 1500);
}

function borra() {
    document.getElementById("text").innerText = "";
}
