$("button#boto2").on("click", function() {
    if (localStorage.getItem('email') !== null) {
        document.body.innerHTML += "L'apariencia guardada es " + localStorage.getItem('email') + " i el tamany de la font és " + localStorage.getItem('credencial') + ".";
    }
    else {
        document.getElementById("formulariLogin").innerHTML +=  "No hi ha cap sessió iniciada";
    }
});