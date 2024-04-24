//event.preventDefault();

$.ajax({
    method: "GET",
    url:"https://jsonplaceholder.typicode.com/users",
    dataType:"json",

    success:function (data) {
        //console.log(data);
        let body = document.getElementsByTagName("tbody")[0];
        //Posem el [0] perquè és un array, i en aquest cas només tenim un tbody i només ens interessa posar la informació al primer

        for(let i = 0; i < data.length; i++) {
            let llista= document.createElement("tr"); //Creem el tr de l'HTML
            let itemId = document.createElement("td"); //Creem cada ítem de td per a cada apartat
            itemId.innerText = data[i].id; //A cada ítem de td li posem la informació adequada
            llista.appendChild(itemId); //I després l'afegim al tr creat anteriorment

            let itemName = document.createElement("td");
            itemName.innerText = data[i].name;
            llista.appendChild(itemName);

            let itemUserName = document.createElement("td");
            itemUserName.innerText = data[i].username;
            llista.appendChild(itemUserName);

            body.appendChild(llista); //Finalment, afegim tota la llista de tr a l'ítem creat anteriorment de "tbody"
        }
        //console.log(body);
        window.stop();
    },
    error:function (jqXHR, textStatus, error){
        console.log(jqXHR);
        alert("Error: " + textStatus + " " + error);
    }
});