<?php
$servername = "localhost";
$username = "root";
$password = "super3";

$conn = new PDO("mysql:host=$servername;dbname=animesexam", $username, $password);
$query = $conn->prepare("select max(idAnimes) as Id from animesexam.animes");
$query->execute();
$result = $query->fetch(PDO::FETCH_ASSOC);

$anime = rand(0, $result["Id"]);
$any = rand(1980, 2023);

$query = $conn->prepare("select Year from animesexam.animes where idAnimes = :id");
$query->bindParam("id", $anime, PDO::PARAM_INT);
$query->execute();
$result = $query->fetch(PDO::FETCH_ASSOC);

$anyAnime = $result["Year"];

$query = $conn->prepare("update animesexam.animes set Year = :any where idAnimes = :num");
$query->bindParam("any", $any, PDO::PARAM_INT);
$query->bindParam("num", $anime, PDO::PARAM_INT);
$query->execute();

echo "L'anime seleccionat és el " . $anime . ". L'any abans de guardar és: " . $anyAnime . '<br>';
echo "L'anime seleccionat és el " . $anime . ". L'any després de guardar és: " . $any;