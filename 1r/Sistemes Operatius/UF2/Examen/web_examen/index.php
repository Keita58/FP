<!DOCTYPE html>
<html>
<head>
  <title>Web examen</title>
</head>
<body>

<h1>HTML</h1>
<p>Si veus això vol dir que la part HTML de la web s'està interpretant bé.</p>
<?php
require("funcions.php");
testPHP();
$conn = connect();
if (!is_null($conn)) {
  testBD($conn);
}
?>
</body>
</html>
