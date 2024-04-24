let persones = [{
    titol: 'Camí al futur',
    autor: 'Bill Gates',
    id: 1254
},
{
    autor: 'Walter Isaacson',
    titol: 'Steve Jobs',
    id: 4264
},
{
    titol: 'L’ocell de la revolta',
    autor: 'Suzanne Collins',
    id: 3245
}]

let sortBy = function(camp, reves, tipus) {
    let variable = tipus? function(x) {
        return tipus (x[camp]);
    } : function(x) {
        return x[camp];
    }
    reves = !reves? 1 : -1;
    return function(x,y) {
        return x = variable(x), variable(y), reves*((x>y)-(x<y));
    }
}
let aaa = persones.sort(sortBy('id', false, parseInt));
let b = ""
for(let i = 0; i < aaa.length; i++) {
    b += "<p>"+ aaa[i].autor + "</p> <p>" + aaa[i].titol + "</p> <p>" + aaa[i].id + "</p>";
}
document.body.innerHTML = b;