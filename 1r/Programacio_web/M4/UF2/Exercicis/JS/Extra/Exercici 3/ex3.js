function highlight() {
    document.body.style.backgroundColor = "black";
    document.body.style.color = "white";
    let text = document.getElementsByTagName("strong");
    for(let i = 0; i < text.length; i++) {
        text[i].style.color = "lime";
        text[i].style.fontSize = "20px";
    }
}

function return_normal() {
    document.body.style.backgroundColor = "transparent";
    document.body.style.color = "black";
    let text = document.getElementsByTagName("strong");
    for(let i = 0; i < text.length; i++) {
        text[i].style.color = "black";
        text[i].style.fontSize = "16px";
    }
}