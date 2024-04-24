$("button#botoAfegirCanco").on("click", function() {
    event.preventDefault();
    $.ajax({
        method: "POST",
        url: "afegir.php",
        data: {"canco": $("#canco").val()},
        dataType: "json",

        success:function (data) {
            console.log(data);
            let a = document.getElementById("a");
            let b = document.getElementById("b");
            b.innerHTML = "";
            if(data[0].estat === 'OK')
                a.innerHTML = "La cançó " + data[0].nom + " s'ha afegit correctament";
            else
                a.innerHTML = data[0].error;

            setTimeout(reset, 2000);
            window.stop();
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