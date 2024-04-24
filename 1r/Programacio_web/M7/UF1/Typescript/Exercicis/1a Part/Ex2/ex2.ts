let boto2 : HTMLButtonElement = document.getElementById("boto") as HTMLButtonElement;
let numero2 : HTMLInputElement = document.getElementById("num") as HTMLInputElement;
let opcio : HTMLInputElement = document.getElementById("opcio") as HTMLInputElement;

boto2.addEventListener('click', (event) => {
    event.preventDefault();
    let res: HTMLElement = document.getElementById("a") as HTMLElement;
    if(opcio.value == "a")
        res.innerHTML = numero2.value + " atmòsferes son " + parseInt(numero2.value)*1.01325 + " bars." + '<br>' + "La conversió que s'ha realitzar ha sigut 1 atm = 1.01325 bar";
    else
        res.innerHTML = numero2.value + " bars son " + parseInt(numero2.value)*0.9869232667 + " atmòsferes." + '<br>' + "La conversió que s'ha realitzar ha sigut 1 bar = 0.9869232667 atm";
});