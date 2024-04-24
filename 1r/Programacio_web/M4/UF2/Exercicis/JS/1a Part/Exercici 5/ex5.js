let compra = prompt("Quin es el preu de la teva compra?")
let pressupost = 100

if(compra > pressupost) {
    document.getElementById("message").innerHTML = "La compra no es pot realitzar perquè el pressupost es de "+compra+" i el pressupost es de "+pressupost;
}
else {
    document.getElementById("message").innerHTML = "La compra es pot realitzar sense problemes ja que es menor o igual al pressupost de "+pressupost;
}
