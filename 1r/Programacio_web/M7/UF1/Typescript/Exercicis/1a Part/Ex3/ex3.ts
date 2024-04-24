let boto3 : HTMLButtonElement = document.getElementById("boto") as HTMLButtonElement;
let paraula : HTMLInputElement = document.getElementById("text") as HTMLInputElement;
let vocalsa : number;
let vocalse : number;
let vocalsi : number;
let vocalso : number;
let vocalsu : number;

boto3.addEventListener('click', (event) => {
    event.preventDefault();
    let a : RegExpMatchArray = paraula.value.match(/[a]/gi) as RegExpMatchArray; //Regex per les 5 vocals, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    vocalsa = a === null ? 0 : a.length;
    let e : RegExpMatchArray = paraula.value.match(/[e]/gi) as RegExpMatchArray; //Regex per les 5 vocals, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    vocalse = e === null ? 0 : e.length;
    let i : RegExpMatchArray = paraula.value.match(/[i]/gi) as RegExpMatchArray; //Regex per les 5 vocals, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    vocalsi = i === null ? 0 : i.length;
    let o : RegExpMatchArray = paraula.value.match(/[o]/gi) as RegExpMatchArray; //Regex per les 5 vocals, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    vocalso = o === null ? 0 : o.length;
    let u : RegExpMatchArray = paraula.value.match(/[u]/gi) as RegExpMatchArray; //Regex per les 5 vocals, la g es epr a tot l'string i la i es per mirar totes les vocals, siguin majúscules o minúscules (case insensitive)
    vocalsu = u === null ? 0 : u.length;

    let res : HTMLElement = document.getElementById("res") as HTMLElement;
    res.innerHTML = "En el text anterior hi havia " + vocalsa + " 'a', " + vocalse + " 'e', " + vocalsi + " 'i', " + vocalso + " 'o' i " + vocalsu + " 'u'.";
});

