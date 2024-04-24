$("button#login").on("click", function() {
    event.preventDefault();
    $.ajax({
        method:"POST",
        url:"login.php",
        data:{"nom": $("#nom").val(), "credencial": $("#credencial").val()},
        dataType:"json",

        success:function (data){
            console.log(data);
            console.log("aaaaaaaaaaa");
            document.body.innerHTML += '<p id="a">';
            let a = document.getElementById("a");
            a.append(data.estat);
            a.innerHTML += '<br>';
            a.append(data.error);
            a.innerHTML += '<br>';

            if(data.estat === 'OK')
                a.innerHTML += '<span style="color: green">' + data.nom;
            else
                a.innerHTML += '<span style="color: red">' + data.nom;

            window.stop();
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);

        }
    })
});