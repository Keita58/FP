<?php

$numero = $_GET["num"]; //Això és el que rebem del json (és un string, pel que ho hem de transformar)
$onze = false;
$disset = false;

if($numero%11 == 0)
    $onze = true;
if($numero%17 == 0)
    $disset = true;

if($onze && $disset)
    echo json_encode("El número és el: ".$numero."</br>" . "El número és múltiple d'11 i 17"."</br>");
else if($onze)
    echo json_encode("El número és el: ".$numero."</br>" . "El número és múltiple d'11"."</br>");
else if($disset)
    echo json_encode("El número és el: ".$numero."</br>" . "El número és múltiple d'17"."</br>");
else
    echo json_encode("El número és el: ".$numero."</br>" . "No és múltiple ni d'11 ni de 17"."</br>");
