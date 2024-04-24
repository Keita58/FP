for(let i = 0; i < 10; i++) {
    let num1 = Math.floor(Math.random() * 256);
    let num2 = Math.floor(Math.random() * 256);
    let num3 = Math.floor(Math.random() * 256);
    let a = "rgb(" + num1 + "," + num2 + "," + num3 + ")";
    document.body.innerHTML += "<p id='b"+ Number(i+1) + "'> Text numero " + Number(i+1) +"</p>";
    document.getElementById('b' + Number(i+1)).style.background = a;
}