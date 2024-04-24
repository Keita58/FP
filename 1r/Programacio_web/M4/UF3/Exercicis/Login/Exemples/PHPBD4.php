<?php
$servername = "localhost";
$username = "root";
$password = "super3";
$correu = $_GET['correu'];
try {
    $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
    $query =$conn->prepare ("select * from usuaris_app where email =:correu");
    $query->bindParam("correu",$correu,PDO::PARAM_STR);
    $query->execute();
    $result = $query->fetchAll(PDO::FETCH_ASSOC);
    //$result = $query->fetch(PDO::FETCH_ASSOC);
    if($query->rowCount() >= 1) {

        print_r(json_encode($result)) ;

    } else{
        print_r(json_encode("No hi ha registres."));
    }


} catch(PDOException $e) {
    print_r(json_encode("Connection failed: " . $e->getMessage()));
}