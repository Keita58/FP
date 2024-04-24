<?php
$servername = "localhost";
$username = "root";
$password = "super3";

$mail = $_POST["correu"];
$contrasenya = $_POST["contrasenya"];
$usuari = $_POST["usuari"];
$contrasenyes = array("estat" => "", "error" => "", "nom" => $mail);

try {
    $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
    $query = $conn->prepare("select * from usuaris_app where nom =:nom");
    $query->bindParam("nom", $mail, PDO::PARAM_STR);
    $query->execute();
    $result = $query->fetch(PDO::FETCH_ASSOC);

    if(!empty($usuari)) {
        $contrasenyes["estat"] = "USER";
        $contrasenyes["error"] = "Ja hi ha un usuari connectat.";
        print_r(json_encode($contrasenyes));
    }
    else if ($result == "") {
        $contrasenyes["estat"] = "KO";
        $contrasenyes["error"] = "L'usuari no existeix";
        print_r(json_encode($contrasenyes));
    } else if ($result["nom"] == $mail && $result["password"] == $contrasenya && empty($usuari)) {
        $contrasenyes["estat"] = "OK";
        print_r(json_encode($contrasenyes));
    } else if ($result["nom"] == $mail && $result["password"] != $contrasenya) {
        $contrasenyes["estat"] = "KO";
        $contrasenyes["error"] = "Credencial incorrecte";
        print_r(json_encode($contrasenyes));
    }

} catch (PDOException $e) {
    print_r(json_encode("Connection failed: " . $e->getMessage()));
}