<?php
$servername = "localhost";
$username = "root";
$password = "super3";

$canco = $_POST["canco"];
$cancons = array();

try {
    $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
    $query = $conn->prepare('select * from songs where Nom = ?');
    $query->bindParam(1, $canco, PDO::PARAM_STR);
    $query->execute();
    $result = $query->fetch(PDO::FETCH_ASSOC);

    if(empty($result) && !empty($canco)) {
        $query = $conn->prepare("insert into songs (Nom) values (?)");
        $query->bindParam(1, $canco, PDO::PARAM_STR);
        $query->execute();
        $aux = array("estat" => "OK", "error" => "", "nom" => $canco);
        array_push($cancons, $aux);
        print_r(json_encode($cancons));
    }
    else if(empty($canco)) {
        $aux = array("estat" => "KO", "error" => "No has posat cap cançó", "nom" => "");
        array_push($cancons, $aux);
        print_r(json_encode($cancons));
    }
    else if($result["Nom"] === $canco) {
        $aux = array("estat" => "KO", "error" => "Cançó existent", "nom" => $canco);
        array_push($cancons, $aux);
        print_r(json_encode($cancons));
    }
} catch (PDOException $e) {
    print_r(json_encode("Connection failed: " . $e->getMessage()));
}

