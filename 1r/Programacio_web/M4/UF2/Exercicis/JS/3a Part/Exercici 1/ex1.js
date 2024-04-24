function getFormvalue(event) {
    let form = document.getElementById("form1");
    let text = document.getElementById("text");
    let cadena = "";
    for(let i = 0; i < form.length; i++) {
        if(form.elements[i].name === 'nom') //Aquest name fa referència al name del HTML, el que dona nom a cada apartat del formulari
            cadena +=  "Nom: " + form.elements[i].value + "&#13;";
        if(form.elements[i].name === 'cognom1') //Aquest name fa referència al name del HTML, el que dona nom a cada apartat del formulari
            cadena +=  "Cognom 1: " + form.elements[i].value + "&#13;";
        if(form.elements[i].name === 'cognom2') //Aquest name fa referència al name del HTML, el que dona nom a cada apartat del formulari
            cadena +=  "Cognom 2: " + form.elements[i].value + "&#13;";
    }
    if(cadena.length > 0)
        text.innerHTML += cadena;
    event.preventDefault();
}