let importsNoIva = prompt("Introdueix el teu import sense IVA")
document.getElementById("aaa").innerHTML = "Preu sense IVA "+importsNoIva
let importsIva = prompt("Quin IVA vols aplicar?")
document.getElementById("bbb").innerHTML = "IVA "+importsIva
let importAmbIva = importsNoIva*(1+(importsIva/100))
document.getElementById("ccc").innerHTML = "Preu amb IVA "+ "<span id='ff'>"+importAmbIva+"</span>"

if(importAmbIva > 100) {
    document.getElementById("ff").style.background="red"
    document.getElementById("ff").style.fontWeight="bold"
}
else {
    document.getElementById("ff").style.background="lime"
    document.getElementById("ff").style.fontStyle="italic"
}