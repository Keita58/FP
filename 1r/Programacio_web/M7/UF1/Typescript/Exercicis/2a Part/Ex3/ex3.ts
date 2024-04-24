let pos : HTMLElement = document.createElement("div");

enum Colors {
    Vermell = "red",
    Blau = "blue",
    Verd = "darkgreen",
    Porpra = "darkviolet",
    Groc = "yellow"
}
let constKeys: string[] = Object.keys(Colors)/*.filter((v) => isNaN(Number(v)))*/;
//                               Ens retorna tots els valors de l'enum
//                                            Amb el filter podem obtenir només les paraules

let formulari : HTMLFormElement = document.createElement("form");
let label : HTMLLabelElement = document.createElement("label");
let seleccio : HTMLSelectElement = document.createElement("select");
let noms : Colors[] = Object.values(Colors);

label.innerText = "Color seleccionat: ";
seleccio.id = "llista";
formulari.appendChild(label);
formulari.appendChild(seleccio);

constKeys.forEach((key, value) => {
    let info : HTMLOptionElement = document.createElement("option");
    info.value = noms[value];
    info.text = `${key}`;
    seleccio.add(info);
})

pos.appendChild(formulari);
document.body.appendChild(pos);

let quadrat : HTMLDivElement = document.createElement("div");

quadrat.style.width = "100px";
quadrat.style.height = "100px";
quadrat.style.backgroundColor = "red";
quadrat.style.border = "3px solid black";
document.body.appendChild(quadrat);

let canvi : HTMLSelectElement = document.getElementById("llista") as HTMLSelectElement;

canvi.addEventListener('change', (event) => {
    event.preventDefault();
    console.log(canvi.value);
    quadrat.style.backgroundColor = canvi.value;
});

