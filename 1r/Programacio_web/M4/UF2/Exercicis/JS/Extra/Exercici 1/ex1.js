let files = prompt("Indica el número de files");
let columnes = prompt("Indica el número de columnes");
let taula = "<table><tr>";

for(let i = 0; i < columnes; i++) {
    taula += "<th>Columna " + Number(i+1) + "</th>";
}
taula += "</tr>";

for(let i = 0; i < files; i++) {
    taula += "<tr>";
    for(let j = 0; j < columnes; j++) {
        let random = Math.floor(Math.random() * 11);
        taula += "<th>" + random + "</th>";
    }
    taula += "</tr>";
}
taula += "</table>"
document.body.innerHTML = taula;