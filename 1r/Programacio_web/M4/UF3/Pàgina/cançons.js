$("button#boto").on("click", function() {
    event.preventDefault();
    $.ajax({
        method: "POST",
        url: "cançons.php",
        data: {"canco": $("#canco").val()},
        dataType: "json",

        success:function (data) {
            console.log(data);
            if(data[0].id === null) {
                let a = document.getElementById("recaptacio");
                a.innerHTML = '<p>' + "La cançó que has escrit no està a la base de dades. Si us plau, escriu una cançó vàlida" + '</p>';
                setTimeout(reset, 2000);
            }
            else {
                let taula = "<table><tr>";
                //Els noms de les columnes
                taula += "<th>Id</th>";
                taula += "<th>Nom</th>";
                taula += "<th>Recaptació</th>";
                taula += "</tr>";

                for(let i = 0; i < data.length; i++) {
                    taula += "<tr><td style='padding: 0 15px'>" + data[i].id + "</td><td style='padding: 0 15px'>" + data[i].nom + "</td><td style='padding: 0 15px'>" + data[i].recaptacio + "</td></tr>"
                }
                taula += "</table>";
                document.getElementById("recaptacio").innerHTML = taula;
            }
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);
        }
    });
});

function reset() {
    location.reload();
}