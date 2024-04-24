$("#guardar").on("click", function(event) {
    event.preventDefault();
    $.ajax({
        method: "GET",
        url:"ex1.php",
        data:{"num": $("#numero").val()}, //Això és el que passem al PHP
        dataType:"json",

        success:function (data) {
            console.log(data);

            document.body.innerHTML = data;

            window.stop();
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);
        }
    });
});
