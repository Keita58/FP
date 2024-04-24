<?php
$servername = "localhost";
$username = "root";
$password = "super3";

$nom = $_POST["anime"];
$array = array();

$conn = new PDO("mysql:host=$servername;dbname=animesexam", $username, $password);
$query = $conn->prepare("select idAnimes from animes where Nom =:nom");
$query->bindParam("nom", $nom, PDO::PARAM_STR);
$query->execute();
$id = $query->fetch(PDO::FETCH_ASSOC);

if(empty($id)) {
    $array[] = array("tipus" => "KO", "nom" => "", "valoracio" => "");
}
else {
    $query = $conn->prepare("select Personatges from animes where Nom =:nom");
    $query->bindParam("nom", $nom, PDO::PARAM_STR);
    $query->execute();
    $result = $query->fetch(PDO::FETCH_ASSOC);
    $a = json_decode($result["Personatges"], true);

    $valoracio = rand(0,10);
    $aux = array("personatge" => $nom, "rate" => $valoracio);
    $a[] = $aux;
    $codificat = json_encode($a);
    $query = $conn->prepare("update animes set Personatges = :personatge where Nom =:nom");
    $query->bindParam("personatge", $codificat, PDO::PARAM_STR);
    $query->bindParam("nom", $nom, PDO::PARAM_STR);
    $query->execute();

    $array[] = array("tipus" => "OK", "nom" => $id["idAnimes"], "valoracio" => $valoracio);
}
print_r(json_encode($array));
