<?php
$servername = "localhost";
$username = "root";
$password = "super3";
$correu = 'marc@ies-sabadell.cat';
try {
    $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
    $query =$conn->prepare ("select * from usuaris_app where email =:correus");
    $query->bindParam("correus",$correu,PDO::PARAM_STR);
    $query->execute();
    $result = $query->fetch(PDO::FETCH_ASSOC);
    if($query->rowCount() >= 1) {

        print_r($result) ;

    } else{
        print_r("No hi ha registres.");
    }


} catch(PDOException $e) {
    print_r("Connection failed: " . $e->getMessage());
}