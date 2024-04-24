let html = '<ul>';
let rep = 0;
for(let i = 0; i < 10; i++) {
    let num = Math.floor(Math.random() * 11);
    if(num === 5) {
        rep++;
    }
    html = html + '<li>' + num + '</li>';
}
html = html + '</ul>';
html = html + "El nombre 5 ha sortit " + rep + " vegades en 10 tirades";
document.body.innerHTML = html;