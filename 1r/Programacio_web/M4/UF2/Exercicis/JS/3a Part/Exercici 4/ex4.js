let slider = document.getElementById("selector")
document.documentElement.setAttribute('data-theme', 'noterrorista')
function canviEstil() {
    if(slider.checked === true)
        document.documentElement.setAttribute('data-theme', 'terrorista');
    else
        document.documentElement.setAttribute('data-theme', 'noterrorista');
}