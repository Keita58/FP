$("button#botoAfegirReproduccio").on("click", function() {
    event.preventDefault();
    $.ajax({
        method: "POST",
        url: "reproduccio.php",
        dataType: "json",

        success:function (data) {
            console.log(data);
            let a = document.getElementById("a");
            let b = document.getElementById("b");
            a.innerHTML = '<p>' + data[0].missatge + '</p>';
            b.innerHTML = '<p>' + "Cançó: " + data[0].canco + '</p>';

            //setTimeout(reset, 2000);
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