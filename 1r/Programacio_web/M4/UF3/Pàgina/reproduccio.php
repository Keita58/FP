<?php
$servername = "localhost";
$username = "root";
$password = "super3";

$cancons = array();

try {
    $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
    $query = $conn->prepare('select max(Id) as Num from usuaris_app');
    $query->execute();
    $result = $query->fetch(PDO::FETCH_ASSOC);
    $usuari = rand(1, $result['Num']); //Usuari random de la llista d'usuaris

    $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
    $query = $conn->prepare('select count(Nom) as Num from songs');
    $query->execute();
    $result = $query->fetch(PDO::FETCH_ASSOC);
    $canco = rand(1, $result['Num']); //Cançó random de la llista de cançons

    $conn = new PDO("mysql:host=$servername;dbname=albums", $username, $password);
    $query = $conn->prepare('select Nom from songs where idSongs = ?');
    $query->bindParam(1, $canco, PDO::PARAM_INT);
    $query->execute();
    $result = $query->fetch(PDO::FETCH_ASSOC);
    $nomCanco = $result["Nom"];

    $query = $conn->prepare('select * from usuaris_songs where idusuari = ?');
    $query->bindParam(1, $usuari, PDO::PARAM_INT);
    $query->execute();
    $resultBuscaUsuari = $query->fetch(PDO::FETCH_ASSOC);

    if(!empty($resultBuscaUsuari)) {
        $query = $conn->prepare("select reproduccions from usuaris_songs where idusuari = ?");
        $query->bindParam(1, $usuari, PDO::PARAM_INT);
        $query->execute();
        $z = $query->fetch(PDO::FETCH_ASSOC);
        $a = json_decode($z["reproduccions"], true);

        $trobat = false;
        foreach ($a as $doble) {
            if ($doble['song'] === $canco)
                $trobat = true;
        }
    }

    if(empty($resultBuscaUsuari)) {
        $aux = array("song" => $canco, "reproduccions" => rand(1,10));
        $codificat = json_encode($aux);
        $codificat = "[".$codificat."]";
        $query = $conn->prepare("insert into usuaris_songs (idusuari, reproduccions) values (?, ?)");
        $query->bindParam(1, $usuari, PDO::PARAM_INT);
        $query->bindParam(2, $codificat, PDO::PARAM_STR);
        $query->execute();

        $query = $conn->prepare("update songs set Reproduccions = Reproduccions + 1, Recaptacio = Recaptacio + 2 where idSongs = ?");
        $query->bindParam(1, $canco, PDO::PARAM_INT);
        $query->execute();
        $b = array("missatge" => "S'ha afegit un nou registre a la BD per les reproduccions de l'usuari " . $usuari, "canco" => $nomCanco);
        array_push($cancons, $b);
        print_r(json_encode($cancons));
    }
    else if(!$trobat) {
        $aux = array("song" => $canco, "reproduccions" => rand(1,10));
        array_push($a, $aux);
        $codificat = json_encode($a);
        $query = $conn->prepare("update usuaris_songs set reproduccions = ? where idusuari = ?");
        $query->bindParam(1, $codificat, PDO::PARAM_STR);
        $query->bindParam(2, $usuari, PDO::PARAM_INT);
        $query->execute();

        $query = $conn->prepare("update songs set Reproduccions = Reproduccions + 1, Recaptacio = Recaptacio + 2 where idSongs = ?");
        $query->bindParam(1, $canco, PDO::PARAM_INT);
        $query->execute();

        $c = array("missatge" => "S'ha afegit una canço a les reproduccions de l'usuari " . $usuari, "canco" => $nomCanco);
        array_push($cancons, $c);
        print_r(json_encode($cancons));
    }
    else {
        for($i = 0; $i < sizeof($a); $i++) {
            if ($a[$i]["song"] === $canco)
                $a[$i]["reproduccions"] += 1;
        }
        $codificat = json_encode($a);
        $query = $conn->prepare("update songs set Reproduccions = Reproduccions + 1, Recaptacio = Recaptacio + 2 where idSongs = ?");
        $query->bindParam(1, $canco, PDO::PARAM_INT);
        $query->execute();

        $query = $conn->prepare("update usuaris_songs set reproduccions = ? where idusuari = ?");
        $query->bindParam(1, $codificat, PDO::PARAM_STR);
        $query->bindParam(2, $usuari, PDO::PARAM_INT);
        $query->execute();

        $d = array("missatge" => "L'usuari " . $usuari . " ha incrementat les reproduccions d'una cançó", "canco" => $nomCanco);
        array_push($cancons, $d);
        print_r(json_encode($cancons));
    }
} catch (PDOException $e) {
    print_r(json_encode("Connection failed: " . $e->getMessage()));
}
