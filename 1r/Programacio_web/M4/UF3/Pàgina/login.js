let foto = document.getElementById("foto");
foto.style.width = "100px";
foto.style.height = "80px";

$("button#boto1").on("click", function() {
    event.preventDefault();
    $.ajax({
        method: "POST",
        url: "login.php",
        data: {"correu": $("#mail").val(), "contrasenya": $("#contrasenya").val(), "usuari": localStorage.getItem('usuari')},
        dataType: "json",

        success:function (data) {
            let a = document.getElementById("formulariLogin");
            a.innerHTML = '<p>';
            a.append(data.estat);
            a.innerHTML += '<br>';
            a.append(data.error);
            a.innerHTML += '<br>';

            if(data.estat === 'OK') {
                a.innerHTML += '<span style="color: green">' + data.nom;
                localStorage.setItem('usuari', data.nom);
            }
            else if(data.estat === 'KO')
                a.innerHTML += '<span style="color: red">' + data.nom;
            else {
                a.innerHTML += '<span style="color: red">' + "L'usuari actual es el " + localStorage.getItem('usuari');
            }

            a.innerHTML += '</p>';

            setTimeout(reset, 2000);
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);
        }
    });
});

$("button#boto3").on("click", function() {
    if (localStorage.getItem('usuari') !== null) {
        localStorage.removeItem('usuari');
        document.getElementById("formulariLogin").innerHTML = '<h3 class="d-flex justify-content-center mt-3 text-success">' + "Has sortit de la teva sessió" + '</h3>';
    }
    else {
        document.getElementById("formulariLogin").innerHTML = '<h3 class="d-flex justify-content-center mt-3 text-danger">' + "No hi ha cap sessió iniciada" + '</h3>';
    }
    setTimeout(reset, 2000);

});

function reset() {
    location.reload();
}