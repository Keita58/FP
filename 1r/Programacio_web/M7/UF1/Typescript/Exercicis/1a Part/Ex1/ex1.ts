let boto : HTMLButtonElement = document.getElementById("a") as HTMLButtonElement;
let numero : HTMLInputElement = document.getElementById("num") as HTMLInputElement;

boto.addEventListener('click', (event) => {
    event.preventDefault();
    let num1 : number;
    num1 = Math.floor(Math.random() * (101 - 2) + 2);
    let res : HTMLElement = document.getElementById("res") as HTMLElement;

    if(parseInt(numero.value) >= 1 && parseInt(numero.value) <= num1) {
        res.innerHTML = "El número " + numero.value + " està dins del rang " + 1 + " i " + num1;
    }
    else {
        res.innerHTML = "El número " + numero.value + " NO està dins del rang " + 1 + " i " + num1;
    }
});