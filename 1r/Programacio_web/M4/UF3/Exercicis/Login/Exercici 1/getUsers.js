$("button#getUsers").on("click", function(){
    event.preventDefault();
    $.ajax({
        method: "GET",
        url:"getUsers.php",
        dataType:"json",

        success:function (data){
            console.log(data);
            document.body.innerHTML += '<p id="a">';
            let a = document.getElementById("a");
            for(let i = 0; i < data.length; i++) {
                a.innerHTML += 'Correu: ' + data[i].email + ' ' + 'Credencial: ' + data[i].credencial + "<br>";
            }
            window.stop();
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);

        }
    });
});