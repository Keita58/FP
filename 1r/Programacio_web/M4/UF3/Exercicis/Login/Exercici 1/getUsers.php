<?php
$servername = "localhost";
$username = "root";
$password = "super3";

/*$nom = $_GET["nom"];
$credencial = $_GET["credencial"];
$contrasenyes = Array("nom"=>$nom, "credencial"=>$credencial);*/

try {
    $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
    $query =$conn->prepare ("select * from usuaris_app");
    $query->execute();
    $result = $query->fetchAll(PDO::FETCH_ASSOC);

    if($query->rowCount() >= 1) {
        print_r(json_encode($result)) ;
    } else{
        print_r(json_encode("No hi ha registres."));
    }


} catch(PDOException $e) {
    print_r(json_encode("Connection failed: " . $e->getMessage()));
}