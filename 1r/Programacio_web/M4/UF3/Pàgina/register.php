<?php
$servername = "localhost";
$username = "root";
$password = "super3";

$nom = $_POST["correu"];
$credencial = $_POST["contrasenya"];
$contrasenyes = array(array("estat" => "", "error" => "", "nom" => $nom));

try {
    $pattern = "/^[a-zA-Z0-9._%+-]+@ies-sabadell.cat$/";
    $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
    $query = $conn->prepare("select * from usuaris_app where nom = ?");
    $query->bindParam(1, $nom, PDO::PARAM_STR);
    $query->execute();
    $result = $query->fetch(PDO::FETCH_ASSOC);

    if (preg_match($pattern, $nom) && $result == "") {
        $query = $conn->prepare("insert into usuaris_app (nom, password) values(?, ?)");
        $query->bindParam(1, $nom, PDO::PARAM_STR);
        $query->bindParam(2, $credencial, PDO::PARAM_STR);
        $query->execute();
        $result = $query->fetch(PDO::FETCH_ASSOC);
        //$result = $query->fetch(PDO::FETCH_ASSOC);
        $contrasenyes[0]["estat"] = "OK";
        print_r(json_encode($contrasenyes));
    } else if (preg_match($pattern, $nom) && $result != "") {
        $contrasenyes[0]["estat"] = "KO";
        $contrasenyes[0]["error"] = "L'usuari existeix";
        print_r(json_encode($contrasenyes));
    } else {
        $contrasenyes[0]["estat"] = "KO";
        $contrasenyes[0]["error"] = "Correu incorrecte";
        print_r(json_encode($contrasenyes));
    }


} catch (PDOException $e) {
    print_r(json_encode("Connection failed: " . $e->getMessage()));
}