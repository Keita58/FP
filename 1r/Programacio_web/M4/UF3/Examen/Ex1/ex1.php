<?php

$array = array();
$n1 = $n2 = $n3 = false;
$numero1 = rand(0, 10);
if($numero1 >=5 )
    $n1 = true;
$numero2 = rand(0, 10);
if($numero2 >=5 )
    $n2 = true;
$numero3 = rand(0, 10);
if($numero3 >=5 )
    $n3 = true;

array_push($array, array("appearence"=>"dark", "nota"=>$numero1));

array_push($array, array("appearence"=>"light", "nota"=>$numero2));

array_push($array, array("appearence"=>"pink", "nota"=>$numero3));

echo "Els colors son: " . '<br>';
print_r($array);
echo '<br>';
if($n1)
    echo "L'apariencia dark ha aprovat amb un " . $numero1 . '<br>';
if($n2)
    echo "L'apariencia light ha aprovat amb un " . $numero2 . '<br>';
if($n3)
    echo "L'apariencia pink ha aprovat amb un " . $numero3 . '<br>';