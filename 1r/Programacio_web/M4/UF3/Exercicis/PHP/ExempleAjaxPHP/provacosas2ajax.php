<?php
//$a = $_GET["cosa"];
$a = $_POST["cosa"];
$a2 = $_POST["cosa2"];
/**
 *
 */
echo json_encode($a . " " . $a2);