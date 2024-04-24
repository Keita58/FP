let botoAfegir = document.getElementById("afegir");
let botoBorrar = document.getElementById("borrar");
botoBorrar.addEventListener("click", function remove() {
    let x = document.getElementById("colorSelect");
    x.remove(x.selectedIndex);
})
botoAfegir.addEventListener("click", function add() {
    let element = document.getElementById("text").value;
    let newElement = document.createElement("option");
    newElement.text = element;
    let x = document.getElementById("colorSelect");
    x.options.add(newElement);
})