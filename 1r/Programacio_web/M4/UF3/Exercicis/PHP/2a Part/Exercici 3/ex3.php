<?php

$array = array();

for($i = 0; $i < 4; $i++) {
    $array[$i] = rand(1, 10);
}

echo "L'array és: ";
print_r($array);
echo "<br>";

$numero1 = $_POST["numero1"];
isInArray($array, $numero1);
$numero2 = $_POST["numero2"];
isInArray($array, $numero2);
$numero3 = $_POST["numero3"];
isInArray($array, $numero3);
$numero4 = $_POST["numero4"];
isInArray($array, $numero4);

function isInArray($array, $numero)
{
    $si = false;
    for($i = 0; $i < sizeof($array); $i++) {
        if($array[$i] == $numero)
            $si = true;
    }
    if($si)
        echo "El número ", $numero, " es troba dins l'array.", "<br>";
    else
        echo "El número ", $numero, " NO es troba dins l'array.", "<br>";
}

