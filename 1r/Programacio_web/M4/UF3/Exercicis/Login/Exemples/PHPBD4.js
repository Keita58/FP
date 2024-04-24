$("#guardar").click(function(){
    event.preventDefault();
    $.ajax({
        method:"GET",
        url:"PHPBD4.php",
        data:{"correu": $("#correu").val()},
        dataType:"json",

        success:function (data){
            console.log(data);
            document.body.innerHTML += '<p> Credencial: ' + data[0].credencial
            //document.body.innerHTML += '<p> Credencial: ' + data.credencial
            window.stop();
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);

        }
    })
});