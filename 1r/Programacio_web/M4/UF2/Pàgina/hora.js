function temps() {
    const dia = new Date();
    const dies = ["Diumenge", "Dilluns", "Diamrts", "Dimecres", "Dijous", "Divendres", "Dissabte"]
    let hora = dia.getHours();
    let minuts =  dia.getMinutes();
    let segons = dia.getSeconds();
    let mes = dia.getMonth();
    mes += 1;
    if(hora < 10) {
        hora = "0" + hora;
    }
    if(minuts < 10) {
        minuts = "0" + minuts;
    }
    if(segons < 10) {
        segons = "0" + segons;
    }
    document.getElementById("hora").innerHTML = dies[dia.getDay()] + " " + dia.getDate() + "/" + mes + "/" + dia.getFullYear() + " " + hora + ":" + minuts + ":" + segons;
    setTimeout(function () {temps()}, 1000);
}
temps();
