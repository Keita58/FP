<?php

$array = array();
for($i = 0; $i < 4; $i++) {
    $num = rand(1, 10);
    $array[] = $num;
}
print_r("L'array és: ");
print_r($array);
echo "<br>";
for($i = 0; $i < 4; $i++) {
    $num = rand(1, 10);
    if(array_search($num, $array))
        printf("El nombre ".$num." es troba en l'array."."<br>");
    else
        printf("El nombre ".$num." NO es troba en l'array."."<br>");
}