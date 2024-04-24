"use strict";
let pos = document.createElement("div");
var Colors;
(function (Colors) {
    Colors["Vermell"] = "red";
    Colors["Blau"] = "blue";
    Colors["Verd"] = "darkgreen";
    Colors["Porpra"] = "darkviolet";
    Colors["Groc"] = "yellow";
})(Colors || (Colors = {}));
let constKeys = Object.keys(Colors) /*.filter((v) => isNaN(Number(v)))*/;
//                               Ens retorna tots els valors de l'enum
//                                            Amb el filter podem obtenir només les paraules
let formulari = document.createElement("form");
let label = document.createElement("label");
let seleccio = document.createElement("select");
let noms = Object.values(Colors);
label.innerText = "Color seleccionat: ";
seleccio.id = "llista";
formulari.appendChild(label);
formulari.appendChild(seleccio);
constKeys.forEach((key, value) => {
    let info = document.createElement("option");
    info.value = noms[value];
    info.text = `${key}`;
    seleccio.add(info);
});
pos.appendChild(formulari);
document.body.appendChild(pos);
let quadrat = document.createElement("div");
quadrat.style.width = "100px";
quadrat.style.height = "100px";
quadrat.style.backgroundColor = "red";
quadrat.style.border = "3px solid black";
document.body.appendChild(quadrat);
let canvi = document.getElementById("llista");
canvi.addEventListener('change', (event) => {
    event.preventDefault();
    console.log(canvi.value);
    quadrat.style.backgroundColor = canvi.value;
});
