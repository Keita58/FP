let boto : HTMLButtonElement = document.getElementById("boto") as HTMLButtonElement;
let hora : HTMLInputElement = document.getElementById("hora") as HTMLInputElement;
let minuts : HTMLInputElement = document.getElementById("minuts") as HTMLInputElement;
let segons : HTMLInputElement = document.getElementById("segons") as HTMLInputElement;

boto.addEventListener('click', (event) => {
    event.preventDefault();

    //Creació d'una tupla
    let temps: [number, number, number] = [parseInt(hora.value), parseInt(minuts.value), parseInt(segons.value)];

    if (temps[2] > 59) {
        temps[1] += Math.floor(temps[2] / 60);
        temps[2] = temps[2] % 60;
    }
    if(temps[1] > 59) {
        temps[0] += Math.floor(temps[1] / 60);
        temps[1] = temps[1] % 60;
    }
    if(temps[0] > 23) {
        temps[0] = temps[0] % 24;
    }

    let div : HTMLElement = document.getElementById("div") as HTMLElement;
    div.innerHTML = "";
    for(let i: number = 0; i < temps.length - 1; i++) {
        if(temps[i] < 10)
            div.innerHTML += '0' + temps[i] + ":";
        else
            div.innerHTML += + temps[i] + ":";
    }
    if(temps[temps.length - 1] < 10)
        div.innerHTML += '0' + temps[temps.length - 1];
    else
        div.innerHTML += temps[temps.length - 1];
});

