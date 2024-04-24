<?php

$opcio = $_POST["opcio"];
$array = array('Watermelon','Apple','Strawberry','Banana','Orange','Cherry','Peach');

$num = rand(0, sizeof($array)-1);
$array[$num] = $opcio;

print_r($array);
