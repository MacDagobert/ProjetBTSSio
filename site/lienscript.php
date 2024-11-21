<?php
session_start();
require_once(__DIR__ . '/config/mysql.php');
require_once(__DIR__ . '/databaseconnect.php');
require_once(__DIR__ . '/variables.php');
?>
<!DOCTYPE html>
<html lang="fr">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../Site/style.css">
    <title>Ttrist</title>
</head>

<body>
                <?php require_once(__DIR__ . '/header.php'); ?>


    <div id="gameOverMessage">
        Game Over
    </div>

    <pre id="jeu"></pre> <!-- Zone où la grille sera affichée -->

</body>

</html>