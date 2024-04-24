<?php

$frase = $_POST["frase"];
$count = 0;
$a = array("frase"=>$frase, "vocals"=>0);

$vowels = array("a", "e", "i", "o", "u");
for ($i = 0; $i < strlen($frase); $i++) {
    if (in_array(strtolower($frase[$i]), $vowels)) {
        $count++;
    }
}
$a["vocals"] = $count;
print_r(json_encode($a));