$("button#boto").on("click", function() {
    event.preventDefault();
    $.ajax({
        method: "POST",
        url:"ex3.php",
        data:{"frase": $("#paraula").val()},
        dataType:"json",

        success:function(data) {
            console.log(data);
            document.body.innerHTML += '<p>'+"Hi ha " + data.vocals + " vocals de la frase/paraula: " + data.frase+'</p>';
            window.stop();
        },
        error:function (jqXHR, textStatus, error){
            console.log(jqXHR);
            alert("Error: " + textStatus + " " + error);

        }
    });
});