$("button#boto").on("click", function() {
    event.preventDefault();
    $.ajax({
        method: "POST",
        url: "ex5.php",
        data: {"anime": $("#anime").val()},
        dataType: "json",

        success:function (data) {
            console.log(data);

            if(data[0]["tipus"] === "OK") {
                document.body.innerHTML += '<p>' + "L'anime " + data[0]["nom"] + " s'ha actualitzat. S'ha afegit el personatge " + $("#anime").val() + " i la valoració " + data[0].valoracio + '</p>';
            }
            else if(data[0]["tipus"] === "KO"){
                document.body.innerHTML += '<p>' + "No existeix l'anime " + $("#anime").val() + '</p>';
            }
            window.stop();
        }
    });
});