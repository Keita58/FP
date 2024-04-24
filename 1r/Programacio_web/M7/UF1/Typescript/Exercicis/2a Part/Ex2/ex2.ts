let boto : HTMLButtonElement = document.getElementById("boto") as HTMLButtonElement;
let num : HTMLInputElement = document.getElementById("num") as HTMLInputElement;

boto.addEventListener('click', (event) => {
   event.preventDefault();
   let res : HTMLElement = document.getElementById("res") as HTMLElement;

   if(!isNaN(Number(num.value))) {
       //Number comprova si el value es un número o no, i si no ho és retorna NaN, per això ho encapsulem en un isNaN()
       if(parseInt(num.value) % 2 == 1)
           res.innerHTML = 'El valor introduït: ' + num.value + ' és imparell.';
       else
           res.innerHTML = 'El valor introduït: ' + num.value + ' és parell.';
   }
   else
       res.innerHTML = 'El valor introduït: ' + num.value + ' no és un número.';
});