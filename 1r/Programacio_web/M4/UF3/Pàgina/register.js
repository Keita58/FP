let foto = document.getElementById("foto");
foto.style.width = "100px";
foto.style.height = "80px";

$("button#boto1").on("click", function() {
    event.preventDefault();
    $.ajax({
        method: "POST",
        url: "register.php",
        data: {"correu": $("#mail").val(), "contrasenya": $("#contrasenya").val()},
        dataType: "json",

        success:function (data) {
            console.log(data);
            if(localStorage.getItem('usuaris') == null) {
                let a = document.getElementById("formulariLogin");
                a.innerHTML = '<p>';
                a.append(data[0].estat);
                a.innerHTML += '<br>';
                a.append(data[0].error);
                a.innerHTML += '<br>';

                if (data[0].estat === 'OK') {
                    a.innerHTML += '<span style="color: green">' + data[0].nom;
                    localStorage.setItem('usuari', data[0].nom)
                } else
                    a.innerHTML += '<span style="color: red">' + data[0].nom;
            }
            else {
                let a = document.getElementById("formulariLogin");
                a.innerHTML = '<p class="d-flex justify-content-center text-danger mt-3">' + "Hi ha una sessió oberta actualment. Si es vol registrar tanca la sessió actual." + '</p>';
            }
            setTimeout(espera, 2500);
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);
        }
    });
});

function espera() {
    location.reload();
}