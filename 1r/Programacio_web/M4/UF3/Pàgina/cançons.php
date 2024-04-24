<?php
$servername = "localhost";
$username = "root";
$password = "super3";

$canco = $_POST["canco"];
$cancons = array();

if(!empty($canco)){
    try {
        $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
        $query = $conn->prepare("select idSongs, Nom, Recaptacio from songs where Nom like :canco");
        $query->bindParam("canco", $canco, PDO::PARAM_STR);
        $query->execute();
        $result = $query->fetch(PDO::FETCH_ASSOC);

        if(!empty($result)) {
            $aux = array("id" => $result["idSongs"], "nom" => $result["Nom"], "recaptacio" => $result["Recaptacio"]);
            array_push($cancons, $aux);
            print_r(json_encode($cancons));
        }
        else {
            $aux = array("id" => null, "nom" => null, "recaptacio" => null);
            array_push($cancons, $aux);
            print_r(json_encode($cancons));
        }
    } catch (PDOException $e) {
        print_r(json_encode("Connection failed: " . $e->getMessage()));
    }
}
else{
    try {
        $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
        $query = $conn->prepare("select idSongs, Nom, Recaptacio from songs order by Recaptacio desc, Nom asc, Reproduccions");
        $query->execute();
        $result = $query->fetchAll(PDO::FETCH_ASSOC);

        foreach($result as $row) {
            $aux = array("id" => $row["idSongs"], "nom" => $row["Nom"], "recaptacio" => $row["Recaptacio"]);
            array_push($cancons, $aux);
        }
        print_r(json_encode($cancons));
    } catch (PDOException $e) {
        print_r(json_encode("Connection failed: " . $e->getMessage()));
    }
}