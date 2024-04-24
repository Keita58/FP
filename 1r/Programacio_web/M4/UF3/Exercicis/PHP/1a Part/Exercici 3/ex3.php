<?php

$array = array('Watermelon','Apple','Strawberry','Banana','Orange','Cherry','Peach');

$array2 = array('Hot dog','Pizza','Kebab','Popcorn');

$num = rand(0, sizeof($array2)-1);

$num2 = rand(0, sizeof($array)-1);

$array[$num2] = $array2[$num];

print_r($array);