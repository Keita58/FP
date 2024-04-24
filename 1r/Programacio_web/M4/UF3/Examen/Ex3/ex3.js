$.ajax({
    method: "GET",
    url: "https://fakestoreapi.com/products",
    dataType: "json",

    success:function (data) {
        console.log(data);

        for(let i = 0; i < data.length; i++) {
            if(data[i]["price"] >= 100)
                document.body.innerHTML += '<p>' + "Titol: " + data[i]["title"] + " - Preu: " + data[i]["price"];
        }
    }
});