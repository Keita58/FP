"use strict";
let boto3 = document.getElementById("boto");
let paraula = document.getElementById("text");
let vocalsa;
let vocalse;
let vocalsi;
let vocalso;
let vocalsu;
boto3.addEventListener('click', (event) => {
    event.preventDefault();
    let a = paraula.value.match(/[a]/gi); //Regex per les 5 vocals, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    vocalsa = a === null ? 0 : a.length;
    let e = paraula.value.match(/[e]/gi); //Regex per les 5 vocals, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    vocalse = e === null ? 0 : e.length;
    let i = paraula.value.match(/[i]/gi); //Regex per les 5 vocals, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    vocalsi = i === null ? 0 : i.length;
    let o = paraula.value.match(/[o]/gi); //Regex per les 5 vocals, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    vocalso = o === null ? 0 : o.length;
    let u = paraula.value.match(/[u]/gi); //Regex per les 5 vocals, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    vocalsu = u === null ? 0 : u.length;
    let res = document.getElementById("res");
    res.innerHTML = "En el text anterior hi havia " + vocalsa + " 'a', " + vocalse + " 'e', " + vocalsi + " 'i', " + vocalso + " 'o' i " + vocalsu + " 'u'.";
});
