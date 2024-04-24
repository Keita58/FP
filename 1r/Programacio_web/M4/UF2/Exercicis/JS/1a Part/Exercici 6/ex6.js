let importsNoIva = prompt("Introdueix el teu import sense IVA")
document.getElementById("aaa").innerHTML = "Preu sense IVA "+importsNoIva
let importsIva = prompt("Quin IVA vols aplicar?")
document.getElementById("bbb").innerHTML = "IVA "+importsIva
document.getElementById("ccc").innerHTML = "Preu amb IVA "+ (importsNoIva*(1+(importsIva/100)))

