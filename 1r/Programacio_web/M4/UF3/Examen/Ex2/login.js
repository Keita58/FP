$("button#boto1").on("click", function() {
    if($("#apariencia").val() != '') {
        localStorage.setItem('email', $("#apariencia").val());
        localStorage.setItem('credencial', $("#mida").val());
    }
});

$("button#boto3").on("click", function() {
    if (localStorage.getItem('email') != null) {
        localStorage.removeItem('email');
        localStorage.removeItem('credencial');
        document.getElementById("formulariLogin").innerHTML += "Has sortit de la teva sessió";
    }
    else {
        document.getElementById("formulariLogin").innerHTML += "No hi ha cap sessió iniciada";
    }
});