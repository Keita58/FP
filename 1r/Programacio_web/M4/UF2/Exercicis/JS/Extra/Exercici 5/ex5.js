let flex = document.getElementById("flexbox-container");
let botoAfegir = document.getElementById("afegir");
let botoBorrar = document.getElementById("borrar");
let quadrat;

flex.onmouseover = function(event) {
    target = event.target;
    let idTarget = target.id;
    //let idtarget = target.id;
    if(target.classList.contains("intern")) {
        if(quadrat) {
            quadrat.style.marginTop = "0px";
        }
        target.style.marginTop = "10px";
        quadrat = target;
    }
}
flex.onmouseout = function(event) {
    quadrat.style.marginTop = "0px";
}

botoBorrar.addEventListener('click', function borrar() {
    let children = flex.children;
    flex.removeChild(children[children.length-1]);
});

botoAfegir.addEventListener('click', function afegir() {
   let afegirElement = document.createElement("div");
   afegirElement.innerHTML = flex.children.length + 1;
   afegirElement.classList.add('intern');
   flex.appendChild(afegirElement);
});