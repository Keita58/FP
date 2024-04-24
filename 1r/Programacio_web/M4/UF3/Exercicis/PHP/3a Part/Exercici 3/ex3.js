$("#guardar").on("click", function(event) {
    event.preventDefault();
    $.ajax({
        method: "GET",
        url:"ex3.php",
        data:{"numero1": $("#numero1").val(), "numero2": $("#numero2").val(), "numero3": $("#numero3").val(), "numero4": $("#numero4").val()}, //Això és el que passem al PHP
        dataType:"json",

        success:function (data) {
            console.log(data);

            document.body.innerHTML += "</br>";
            document.body.innerHTML += data.Array;
            document.body.innerHTML += "</br>";
            document.body.innerHTML += data.Numero1;
            document.body.innerHTML += "</br>";
            document.body.innerHTML += data.Numero2;
            document.body.innerHTML += "</br>";
            document.body.innerHTML += data.Numero3;
            document.body.innerHTML += "</br>";
            document.body.innerHTML += data.Numero4;

            window.stop();
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);
        }
    });
});
