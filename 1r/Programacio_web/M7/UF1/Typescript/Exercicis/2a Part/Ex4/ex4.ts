type Barbarian = {
    nom : string,
    punts : number,
    arma : string,
    mal : string,
    tipus : string
}

type Wizard = {
    nom : string,
    punts : number,
    conjuracio : string,
    arcana : string,
    tipus : string
}

type Player = Barbarian | Wizard;
let arrayJugadors : Player[] = [];

let div : HTMLElement = document.createElement("div");
let formulari1 : HTMLFormElement = document.createElement("form");
let formulari2 : HTMLFormElement = document.createElement("form");
let label : HTMLLabelElement = document.createElement("label");
let seleccio : HTMLSelectElement = document.createElement("select");

document.body.appendChild(div);
div.appendChild(formulari1);
div.appendChild(formulari2);
formulari1.appendChild(label);
formulari1.appendChild(seleccio);

let barbar : HTMLOptionElement = document.createElement("option");
let mac : HTMLOptionElement = document.createElement("option");

formulari1.id = 'jugaors';
formulari2.id = 'valors';
formulari2.onsubmit = Enviament;
seleccio.id = 'llista';
seleccio.add(barbar);
seleccio.add(mac);

label.innerText = 'Character: ';
barbar.text = 'Barbarian';
barbar.id = 'Barbarian';
mac.text = 'Wizard';
mac.id = 'Wizard';

let canvi : HTMLSelectElement = document.getElementById("llista") as HTMLSelectElement;
// canvi.value = 'Barbarian'; // Posem això hardcoded perquè es posin les característiques de primeres
personatges(); // Això també, ja que necessitem cridar la funció perquè es mostri per pantalla
canvi.addEventListener('change', personatges);

function personatges() {
    if (canvi.value == 'Wizard') {
        formulari2.innerHTML="";
        //document.getElementById("res").innerHTML = '';
        let nom : HTMLInputElement = document.createElement("input");
        let punts : HTMLInputElement = document.createElement("input");
        let conjuracio : HTMLInputElement = document.createElement("input");
        let arcana : HTMLInputElement = document.createElement("input");

        formulari2.appendChild(nom);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(punts);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(conjuracio);
        formulari2.appendChild(arcana);

        nom.placeholder = 'Nom';
        nom.id = 'nom';
        nom.required = true;
        punts.placeholder = 'Punts';
        punts.id = 'punts';
        punts.required = true;
        conjuracio.placeholder = 'Conjur';
        conjuracio.id = 'conjur';
        conjuracio.required = true;
        arcana.placeholder = 'Tradició arcana';
        arcana.id = 'arcana';
        arcana.required = true;

        let boto1 : HTMLButtonElement = document.createElement("button");
        let boto2 : HTMLButtonElement = document.createElement("button");

        boto1.innerText = "Add";
        boto2.innerText = "Get All";

        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(boto1);
        formulari2.appendChild(boto2);

        boto2.addEventListener('click', (event) => {
            event.preventDefault();
            formulari2.innerHTML += '<br>';
            arrayJugadors.forEach(item => {
                if(item.tipus == "Barbarian") {
                    formulari2.innerHTML += 'El tipus és ' + item.tipus + ', el nom és ' + item.nom + ', els punts són ' + item.punts + ', el dany és ' + item.mal + ', l`arma és ' + item.arma + '.';
                    formulari2.innerHTML += '<br>';
                }
                else if(item.tipus == 'Wizard') {
                    formulari2.innerHTML += 'El tipus és ' + item.tipus + ', el nom és ' + item.nom + ', els punts són ' + item.punts + ', el conjur és ' + item.conjuracio + ', la tradició arcana és ' + item.arcana + '.';
                    formulari2.innerHTML += '<br>';
                }
            });
        });
    }
    else {
        formulari2.innerHTML="";
        //document.getElementById("res").innerHTML = '';
        let nom : HTMLInputElement = document.createElement("input");
        let punts : HTMLInputElement = document.createElement("input");
        let arma : HTMLInputElement = document.createElement("input");
        let mal : HTMLInputElement = document.createElement("input");

        formulari2.appendChild(nom);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(punts);
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(arma);
        formulari2.appendChild(mal);

        nom.placeholder = 'Nom';
        nom.id = 'nom';
        nom.required = true;
        punts.placeholder = 'Punts';
        punts.id = 'punts';
        punts.required = true;
        arma.placeholder = 'Arma';
        arma.id = 'arma';
        arma.required = true;
        mal.placeholder = 'Mal';
        mal.id = 'mal';
        mal.required = true;

        let boto1 : HTMLButtonElement = document.createElement("button");
        let boto2 : HTMLButtonElement = document.createElement("button");

        boto1.innerText = "Add";
        boto2.innerText = "Get All";

        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(document.createElement("br"));
        formulari2.appendChild(boto1);
        formulari2.appendChild(boto2);

        boto2.addEventListener('click', (event) => {
            event.preventDefault();
            formulari2.innerHTML += '<br>';
            arrayJugadors.forEach(item => {
                if(item.tipus == "Barbarian") {
                    formulari2.innerHTML += 'El tipus és ' + item.tipus + ', el nom és ' + item.nom + ', els punts són ' + item.punts + ', el dany és ' + item.mal + ', l`arma és ' + item.arma + '.';
                    formulari2.innerHTML += '<br>';
                }
                else if(item.tipus == 'Wizard') {
                    formulari2.innerHTML += 'El tipus és ' + item.tipus + ', el nom és ' + item.nom + ', els punts són ' + item.punts + ', el conjur és ' + item.conjuracio + ', la tradició arcana és ' + item.arcana + '.';
                    formulari2.innerHTML += '<br>';
                }
            });
        });
    }
}

function Enviament() {
    event.preventDefault();
    if(canvi.value == 'Barbarian') {
        let aux : Barbarian = {
            nom: (document.getElementById('nom') as HTMLInputElement).value,
            punts: ((document.getElementById('punts') as HTMLInputElement).value as unknown as number),
            arma: (document.getElementById('arma') as HTMLInputElement).value,
            mal: (document.getElementById('mal') as HTMLInputElement).value,
            tipus: "Barbarian"
        };
        arrayJugadors.push(aux);
    }
    else {
        let aux : Wizard = {
            nom: (document.getElementById('nom') as HTMLInputElement).value,
            punts: ((document.getElementById('punts') as HTMLInputElement).value as unknown as number),
            conjuracio: (document.getElementById('conjur') as HTMLInputElement).value,
            arcana: (document.getElementById('arcana') as HTMLInputElement).value,
            tipus: "Wizard"
        };
        arrayJugadors.push(aux);
    }

    formulari2.innerHTML += '<br>';
    formulari2.innerHTML += "Guardat!!";
    formulari2.innerHTML += '<br>';
    formulari2.innerHTML += "Espera 1s...";
    setTimeout(personatges, 1500);
}
