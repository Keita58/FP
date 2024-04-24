<?php

$data = new DateTime(); //Avui
echo "Avui és: ", $data->format('d/m/Y');
echo "<br>";

$dataVella = DateTime::createFromFormat('d/m/Y h:i:s','01/12/2023 00:00:00');
echo "Before date és: ", $dataVella->format('d/m/Y');
echo "<br>";

$calcul = date_diff($data, $dataVella);
echo $calcul->m, " mes/os, ",$calcul->d, " dies, ", $calcul->h, " hores, ", $calcul->i, " minuts";