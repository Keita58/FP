"use strict";
let arrayJugadors = [];
let div = document.createElement("div");
let formulari1 = document.createElement("form");
let formulari2 = document.createElement("form");
let label = document.createElement("label");
let seleccio = document.createElement("select");
document.body.appendChild(div);
div.appendChild(formulari1);
div.appendChild(formulari2);
formulari1.appendChild(label);
formulari1.appendChild(seleccio);
let barbar = document.createElement("option");
let mac = document.createElement("option");
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
let canvi = document.getElementById("llista");
// canvi.value = 'Barbarian'; // Posem això hardcoded perquè es posin les característiques de primeres
personatges(); // Això també, ja que necessitem cridar la funció perquè es mostri per pantalla
canvi.addEventListener('change', personatges);
function personatges() {
    if (canvi.value == 'Wizard') {
        formulari2.innerHTML = "";
        //document.getElementById("res").innerHTML = '';
        let nom = document.createElement("input");
        let punts = document.createElement("input");
        let conjuracio = document.createElement("input");
        let arcana = document.createElement("input");
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
        let boto1 = document.createElement("button");
        let boto2 = document.createElement("button");
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
                if (item.tipus == "Barbarian") {
                    formulari2.innerHTML += 'El tipus és ' + item.tipus + ', el nom és ' + item.nom + ', els punts són ' + item.punts + ', el dany és ' + item.mal + ', l`arma és ' + item.arma + '.';
                    formulari2.innerHTML += '<br>';
                }
                else if (item.tipus == 'Wizard') {
                    formulari2.innerHTML += 'El tipus és ' + item.tipus + ', el nom és ' + item.nom + ', els punts són ' + item.punts + ', el conjur és ' + item.conjuracio + ', la tradició arcana és ' + item.arcana + '.';
                    formulari2.innerHTML += '<br>';
                }
            });
        });
    }
    else {
        formulari2.innerHTML = "";
        //document.getElementById("res").innerHTML = '';
        let nom = document.createElement("input");
        let punts = document.createElement("input");
        let arma = document.createElement("input");
        let mal = document.createElement("input");
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
        let boto1 = document.createElement("button");
        let boto2 = document.createElement("button");
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
                if (item.tipus == "Barbarian") {
                    formulari2.innerHTML += 'El tipus és ' + item.tipus + ', el nom és ' + item.nom + ', els punts són ' + item.punts + ', el dany és ' + item.mal + ', l`arma és ' + item.arma + '.';
                    formulari2.innerHTML += '<br>';
                }
                else if (item.tipus == 'Wizard') {
                    formulari2.innerHTML += 'El tipus és ' + item.tipus + ', el nom és ' + item.nom + ', els punts són ' + item.punts + ', el conjur és ' + item.conjuracio + ', la tradició arcana és ' + item.arcana + '.';
                    formulari2.innerHTML += '<br>';
                }
            });
        });
    }
}
function Enviament() {
    event.preventDefault();
    if (canvi.value == 'Barbarian') {
        let aux = {
            nom: document.getElementById('nom').value,
            punts: document.getElementById('punts').value,
            arma: document.getElementById('arma').value,
            mal: document.getElementById('mal').value,
            tipus: "Barbarian"
        };
        arrayJugadors.push(aux);
    }
    else {
        let aux = {
            nom: document.getElementById('nom').value,
            punts: document.getElementById('punts').value,
            conjuracio: document.getElementById('conjur').value,
            arcana: document.getElementById('arcana').value,
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
