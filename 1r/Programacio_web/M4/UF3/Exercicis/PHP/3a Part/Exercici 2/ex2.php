<?php

$menjar = $_GET["opcio"];
$array = array('Watermelon','Apple','Strawberry','Banana','Orange','Cherry','Peach');

$num = rand(0, sizeof($array)-1);
$array[$num] = $menjar;

echo json_encode($array);