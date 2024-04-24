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
//Ara posem les cançons
/*taula += "<tr>";
taula += "<td>Pacific Rim</td>";
taula += "<td>4:55</td>";
taula += "<td>Tom Morello</td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Gipsy Danger</td>";
taula += "<td>3:19</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Canceling The Apocalypse</td>";
taula += "<td>3:39</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Just A Memory</td>";
taula += "<td>2:08</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>2500 Tons Of Awesome</td>";
taula += "<td>1:05</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>The Shatterdome</td>";
taula += "<td>2:31</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Mako</td>";
taula += "<td>4:24</td>";
taula += "<td>Priscilla Ahn</td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Call Me Newt</td>";
taula += "<td>1:43</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Jaeger Tech</td>";
taula += "<td>1:58</td>";
taula += "<td>Tom Morello</td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>To Fight Monsters, We Created Monsters</td>";
taula += "<td>2:04</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Better Than New</td>";
taula += "<td>1:41</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>We Are The Resistance</td>";
taula += "<td>1:49</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Double Event</td>";
taula += "<td>2:28</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Striker Eureka</td>";
taula += "<td>1:55</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Physical Compatibility</td>";
taula += "<td>2:32</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Category 5</td>";
taula += "<td>2:17</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Pentecost</td>";
taula += "<td>2:12</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Go Big Or Go Extinct</td>";
taula += "<td>2:25</td>";
taula += "<td>Tom Morello</td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Hannibal Chau</td>";
taula += "<td>1:34</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>For My Family</td>";
taula += "<td>1:58</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>No Pulse</td>";
taula += "<td>0:58</td>";
taula += "<td>Tom Morello</td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Kaiju Groupie</td>";
taula += "<td>1:15</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Deep Beneath The Pacific</td>";
taula += "<td>1:55</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>The Breach</td>";
taula += "<td>3:15</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>We Need A New Weapon</td>";
taula += "<td>1:41</td>";
taula += "<td></td>";
taula += "</tr>";

taula += "<tr>";
taula += "<td>Drift</td>";
taula += "<td>3:51</td>";
taula += "<td>RZA - Blake Perlman</td>";
taula += "</tr>";

taula += "</table>"*/