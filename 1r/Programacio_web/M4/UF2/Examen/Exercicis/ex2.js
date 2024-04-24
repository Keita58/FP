let llista = document.getElementsByTagName("ol");
let rep = [0,0,0,0,0,0,0,0,0,0,0];
for(let i = 0; i < 12; i++) {
    let num = Math.floor(Math.random() * 11);
    switch(num) {
        case 0:
            rep[0] = rep[0]+1;
            break;
        case 1:
            rep[1] = rep[1]+1;
            break;
        case 2:
            rep[2] = rep[2]+1;
            break;
        case 3:
            rep[3] = rep[3]+1;
            break;
        case 4:
            rep[4] = rep[4]+1;
            break;
        case 5:
            rep[5] = rep[5]+1;
            break;
        case 6:
            rep[6] = rep[6]+1;
            break;
        case 7:
            rep[7] = rep[7]+1;
            break;
        case 8:
            rep[8] = rep[8]+1;
            break;
        case 9:
            rep[9] = rep[9]+1;
            break;
        case 10:
            rep[10] = rep[10]+1;
            break;
    }
    let numeros = document.createElement("li");
    numeros.innerText = num;
    llista[0].appendChild(numeros);
}

let llista2 = document.getElementsByTagName("ul");
for(let i = 0; i < rep.length; i++) {
    let apartat = document.createElement("li");
    apartat.innerHTML += "El nombre " + i + " ha sortit " + rep[i] + " vegades en 12 tirades";
    llista2[0].appendChild(apartat);
}

