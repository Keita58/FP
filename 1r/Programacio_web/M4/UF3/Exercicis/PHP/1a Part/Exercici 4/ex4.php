<?php

$valor = rand(1,10);

$array = array();
echo "<ul>";
for($i = 0; $i < 10; $i++) {
    $array[$i] = rand(1,10);
    echo "<li>", $array[$i], "</li>";
}
echo "</ul>";

$vegades = 0;

for($i = 0; $i < sizeof($array); $i++) {
    if($array[$i] == $valor) {
        $vegades++;
    }
}

echo "El nombre ", "$valor", " ha sortit ", $vegades, " vegades en 10 tirades.";