<?php

$array = array();
$arrayReturn = array();

for($i = 0; $i < 4; $i++) {
    $array[$i] = rand(1, 10);
}

$arrayReturn[0] = "L'array és: " . "</br>" . "[" . implode(",", $array) . "]";

$numero1 = $_GET["numero1"];
$arrayReturn[1] = isInArray($array, $numero1);
$numero2 = $_GET["numero2"];
$arrayReturn[2] = isInArray($array, $numero2);
$numero3 = $_GET["numero3"];
$arrayReturn[3] = isInArray($array, $numero3);
$numero4 = $_GET["numero4"];
$arrayReturn[4] = isInArray($array, $numero4);

function isInArray($array, $numero)
{
    $si = false;
    for($i = 0; $i < sizeof($array); $i++) {
        if($array[$i] == $numero)
            $si = true;
    }
    if($si)
        return("El número " . $numero . " es troba dins l'array." . "<br>");
    else
        return("El número " . $numero . " NO es troba dins l'array." . "<br>");
}

$final = array("Array"=>$arrayReturn[0], "Numero1"=>$arrayReturn[1], "Numero2"=>$arrayReturn[2], "Numero3"=>$arrayReturn[3], "Numero4"=>$arrayReturn[4]);

echo json_encode($final);