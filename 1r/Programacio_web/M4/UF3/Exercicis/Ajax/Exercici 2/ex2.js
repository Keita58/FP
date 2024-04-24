//event.preventDefault();

$.ajax({
    method: "GET",
    url:"https://jsonplaceholder.typicode.com/photos",
    dataType:"json",

    success:function (data) {
        console.log(data);

        let div = document.getElementsByClassName("fotos")[0];

        for(let i = 0; i < data.length; i++) {
            let titol = document.createElement("p");
            titol.innerHTML = "<span style='font-weight: bold; font-size: large'>Títol: </span>" + data[i].title;
            div.append(titol);

            let foto = document.createElement("img");
            foto.src = data[i].thumbnailUrl;
            div.append(foto);
        }
        //console.log(body);
        window.stop();
    },
    error:function (jqXHR, textStatus, error){
        console.log(jqXHR);
        alert("Error: " + textStatus + " " + error);
    }
});