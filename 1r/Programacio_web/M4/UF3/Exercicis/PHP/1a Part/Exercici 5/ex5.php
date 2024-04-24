<?php

$files = rand(1, 10);
$columnes = rand(1, 10);

echo "<h1>", "La taula té ", $files, " i ", $columnes, "</h1>";

echo "<table style='border: 1px solid black; border-collapse: collapse;'>";
echo "<tr>";
for($i = 1; $i <= $columnes; $i++) {
    echo "<th style='border: 1px solid black; border-collapse: collapse;'>", "Columna ", $i, "</th>";
}
echo "</tr>";

for($i = 0; $i < $files; $i++) {
    echo "<tr>";
    for($j = 0; $j < $columnes; $j++) {
        echo "<td style='border: 1px solid black; border-collapse: collapse;'>", rand(1, 10), "</td>";
    }
    echo "</tr>";
}
echo "</table>";