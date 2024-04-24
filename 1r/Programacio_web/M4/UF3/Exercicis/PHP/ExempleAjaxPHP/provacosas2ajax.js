$("#guardar").click(function(){
    event.preventDefault();
    $.ajax({
        method:"POST",

        url:"provacosas2ajax.php",
        data:{"cosa": $("#cosainput").val(), "cosa2": $("#cosa2input").val()},
        dataType:"json",

        success:function (otradata){
            console.log(otradata);

            $("p#result").text(otradata);
            window.stop();
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);

        }
    })
});