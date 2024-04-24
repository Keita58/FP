$.ajax({
    method: "GET",
    url:"https://jsonplaceholder.typicode.com/comments",
    dataType:"json",

    success:function (data) {
        console.log(data);
        for(let i = 0; i < data.length; i++) {
            let titol = document.createElement("p");
            titol.innerHTML = "ID: " + data[i].id + " - Correu: " + data[i].email + " - Nom del comentari: " + data[i].name;
            document.body.append(titol);
        }
        //console.log(body);
        window.stop();
    },
    error:function (jqXHR, textStatus, error){
        console.log(jqXHR);
        alert("Error: " + textStatus + " " + error);
    }
});