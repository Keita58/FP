$("button#register").on("click", function() {
    event.preventDefault();
    $.ajax({
        method:"POST",
        url:"register.php",
        data:{"nom": $("#nom").val(), "credencial": $("#credencial").val()},
        dataType:"json",

        success:function (data){
            console.log(data);
            document.body.innerHTML += '<p id="a">';
            let a = document.getElementById("a");
            a.append(data[0].estat);
            a.innerHTML += '<br>';
            a.append(data[1].error);
            a.innerHTML += '<br>';

            if(data[0].estat === 'OK')
                a.innerHTML += '<span style="color: green">' + data[2].nom;
            else
                a.innerHTML += '<span style="color: red">' + data[2].nom;

            window.stop();
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);

        }
    })
});