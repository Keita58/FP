$("#guardar").on("click", function(event) {
    event.preventDefault();
    $.ajax({
        method: "GET",
        url:"ex2.php",
        data:{"opcio": $("#opcio").val()}, //Això és el que passem al PHP
        dataType:"json",

        success:function (data) {
            console.log(data);

            let body = document.body;
            body.innerHTML += "</br>";
            for(let i = 0; i < data.length; i++) {
                body.append(data[i]);
                body.innerHTML += "</br>";
            }

            window.stop();
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);
        }
    });
});
