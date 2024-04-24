let foto = document.getElementById("foto");
foto.style.width = "100px";
foto.style.height = "80px";

let botoEnvia = document.getElementById("boto1");
let botoCancela = document.getElementById("boto2");

botoEnvia.addEventListener("click", function(element) {
    let correu = document.getElementById("mail").value;
    let contrasenya = document.getElementById("contrasenya").value;
    document.getElementById("text").innerHTML = "<p>L'usuari " + correu + " s'ha registrat amb la contrasenya " + contrasenya +"</p>";
});

botoCancela.addEventListener("click", function(element) {
    document.getElementById("text").innerHTML = "<p>L'usuari ha cancel·lat l'operació.</p>";
});