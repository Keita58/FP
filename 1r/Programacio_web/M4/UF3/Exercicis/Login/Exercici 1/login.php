<?php
$servername = "localhost";
$username = "root";
$password = "super3";

$nom = $_POST["nom"];
$credencial = $_POST["credencial"];
$contrasenyes = Array("estat"=>"", "error"=>"", "nom"=>$nom);

try {
    $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
    $query =$conn->prepare ("select * from usuaris_app where nom =:correu");
    $query->bindParam("correu",$nom,PDO::PARAM_STR);
    $query->execute();
    $result = $query->fetch(PDO::FETCH_ASSOC);

    if($result == "") {
        $contrasenyes["estat"] = "KO";
        $contrasenyes["error"] = "Usuari no existeix";
        print_r(json_encode($contrasenyes));
    }
    else if($result["email"] == $nom && $result["credencial"] == $credencial) {
        $contrasenyes["estat"] = "OK";
        print_r(json_encode($contrasenyes));
    }
    else if($result["email"] == $nom && $result["credencial"] != $credencial) {
        $contrasenyes["estat"] = "KO";
        $contrasenyes["error"] = "Credencial incorrecte";
        print_r(json_encode($contrasenyes));
    }

} catch(PDOException $e) {
    print_r(json_encode("Connection failed: " . $e->getMessage()));
}