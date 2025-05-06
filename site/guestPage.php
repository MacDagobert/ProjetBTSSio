<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../Site/style.css">
    <title>Document</title>
</head>
<body>
    <?php require_once(__DIR__ . '/header.php'); ?>

    <pre id="jeu"></pre>
    <div id="gameOverMessage">
        Game Over
    </div>
    <div id="score">Score : 0</div>
    <div class="button-container">
        <!-- Bouton Se connecter -->
        <button id="buttonReplay" type="button" onclick="window.location.href='guestPage.php'">Rejouer</button>
        <div class="button-container">
        <!-- Bouton Se connecter -->
        <button id="buttonHomepage" type="button" onclick="window.location.href='index.php'">Menu Principal</button>
    <script src="../site/script.js" defer></script>
</body>
</html>