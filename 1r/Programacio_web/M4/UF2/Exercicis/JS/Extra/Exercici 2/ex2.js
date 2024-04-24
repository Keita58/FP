const dia = new Date();
const dies = ["Diumenge", "Dilluns", "Diamrts", "Dimecres", "Dijous", "Divendres", "Dissabte"]
document.body.innerHTML = "Avui es: " + dies[dia.getDay()];
document.body.innerHTML += "<br>" + "Hora actual: " + dia.getHours() + ":" + dia.getMinutes() + ":" + dia.getSeconds();