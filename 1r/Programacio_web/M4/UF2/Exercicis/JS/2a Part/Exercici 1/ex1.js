for (let i = 1; i < 6; i++) {
    for (let j = 1; j < (i+1); j++) {
        let a = document.getElementsByTagName("body").item(0).innerHTML;
        document.getElementsByTagName("body").item(0).innerHTML = a + i;
    }
    let a = document.getElementsByTagName("body").item(0).innerHTML;
    document.getElementsByTagName("body").item(0).innerHTML = a + "<br>";
}