<?php

function testPHP() {
  echo "<h1>PHP</h1>";
  echo "<p>Si veus això vol dir que la part PHP de la web s'està interpretant bé.</p>";
}

function connect() {
  try {
    require("config.php");
    $conn = new PDO("mysql:host=$server;dbname=$db;charset=utf8", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "<h1>BD</h1>";
    echo "<p>Si veus això vol dir que s'ha pogut connectar a la BD.</p>";
    return $conn;
  } catch(PDOException $e) {
    echo "Ha fallat la connexió: " . $e->getMessage();
    return null;
  }
}

function testBD($conn) {
  try {
    $statement = $conn->prepare("SELECT * FROM messages");
    $statement->execute();
    $msg = $statement->fetchAll()[0];
    echo "<h1>{$msg['title']}</h1>";
    echo "<p>{$msg['body']}</p>";
  } catch(PDOException $e) {
    echo "Ha fallat la select: " . $e->getMessage();
  }
}

?>
