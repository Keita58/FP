let boto : HTMLButtonElement = document.getElementById("boto") as HTMLButtonElement;
let frase : HTMLInputElement = document.getElementById("text") as HTMLInputElement;

boto.addEventListener("click", (event) => {
   event.preventDefault();

   let array : Array<String> = frase.value.split(" ") as Array<String>;
   let res : HTMLElement = document.getElementById("res") as HTMLElement;
   res.innerHTML = "";
   for (let i : number = array.length - 1; i >= 0; i--) {
       res.innerHTML += array[i] + " ";
   }
});