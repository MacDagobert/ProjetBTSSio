<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../Site/style.css">
    <title>Document</title>
</head>
<body>
    <?php require_once(__DIR__ . '/header.php'); ?>
    <div id="gameOverMessage" style="display: none;">
        Game Over
    </div>
    <pre id="jeu"></pre>
    <div id="score">Score : 0</div>
    <div class="button-container">
        <!-- Bouton rejouer -->
        <button id="buttonReplay" type="button" onclick="window.location.href='gameLogged.php'">Rejouer</button>
        <div class="button-container">
        <!-- Bouton Index -->
        <button id="buttonHomepage" type="button" onclick="window.location.href='index.php'">Menu Principal</button>
  
    <script src="../site/script.js" defer></script>
</body>
</html>

<!-- Ici j'ai modif dans script fonction start + game over + enregistrez score et record_score.php verif fonction