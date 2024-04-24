<?php

$frase = "Abecedari";
$count = 0;
$vowels = array("a", "e", "i", "o", "u");
for ($i = 0; $i < strlen($frase); $i++) {
    if (in_array(strtolower($frase[$i]), $vowels)) {
        $count++;
    }
}
echo "Hi ha $count vocals de la frase/paraula: $frase";