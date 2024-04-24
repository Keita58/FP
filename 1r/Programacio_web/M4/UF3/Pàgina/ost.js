let taula = "<table><tr>";

//Els noms de les columnes
taula += "<th>Títol</th>";
taula += "<th>Duració</th>";
taula += "</tr>";

//Títols de les cançons
var titols = [
    "Pacific Rim",
    "Gipsy Danger",
    "Canceling the Apocalypse",
    "Just a Memory",
    "2500 Tons of Awesome",
    "The Shatterdome",
    "Mako",
    "Call Me Newt",
    "Jaeger Tech",
    "To Fight Monsters, We Created Monsters",
    "Better Than New",
    "We Are the Resistance",
    "Double Event",
    "Striker Eureka",
    "Physical Compatibility",
    "Category 5",
    "Pentecost",
    "Go Big or Go Extinct",
    "Hannibal Chau",
    "For My Family",
    "No Pulse",
    "Kaiju Groupie",
    "Deep Beneath the Pacific",
    "The Breach",
    "We Need a New Weapon"
];

//Duracions de les cançons
var duracio = [
    "4:53", "3:18", "3:37", "2:07", "1:05", "2:30", "4:23", "1:42", "1:58", "2:03", "1:42", "1:49", "2:27",
    "1:55", "2:31", "2:15", "2:11", "2:24", "1:33", "1:57", "0:58", "1:15", "1:56", "3:14", "1:40"
];

for(var i = 0; i < titols.length; i++) {
    taula += "<tr><td>" + titols[i] + "</td><td>" + duracio[i] + "</td></tr>"
}
taula += "</table>";
document.getElementById("taula").innerHTML = taula;