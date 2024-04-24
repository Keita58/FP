<?php

$numero = $_POST["numero"];
$onze = false;
$disset = false;

if($numero%11 == 0)
    $onze = true;
if($numero%17 == 0)
    $disset = true;

printf("El número és el: ".$numero."</br>");
if($onze && $disset)
    printf("El número és múltiple d'11 i 17"."</br>");
else if($onze)
    printf("El número és múltiple d'11"."</br>");
else if($disset)
    printf("El número és múltiple d'17"."</br>");
else
    printf("No és múltiple ni d'11 ni de 17"."</br>");
