<?php
$servername = "localhost";
$username = "root";
$password = "super3";

try {
    $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
    $query = $conn->prepare("SELECT * FROM usuaris_app");
    $query->execute();

    // Una fila
    $result = $query->fetchAll(PDO::FETCH_ASSOC);
    // Moltes files
    //$result = $query->fetchAll(PDO::FETCH_ASSOC);
    if($query->rowCount() >= 1) {

        print_r($result) ;

    } else{
        print_r("No hi ha registres.");
    }

    print_r($query);

} catch(PDOException $e) {
    print_r("Connection failed: " . $e->getMessage());
}