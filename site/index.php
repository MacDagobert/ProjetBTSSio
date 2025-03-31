<?php
session_start();

// Afficher les messages de succès ou d'erreur
if (isset($_SESSION['SUCCESS_MESSAGE'])) {
    echo '<div class="success-message">' . htmlspecialchars($_SESSION['SUCCESS_MESSAGE']) . '</div>';
    unset($_SESSION['SUCCESS_MESSAGE']);
}
if (isset($_SESSION['LOGIN_ERROR_MESSAGE'])) {
    echo '<div class="error-message">' . htmlspecialchars($_SESSION['LOGIN_ERROR_MESSAGE']) . '</div>';
    unset($_SESSION['LOGIN_ERROR_MESSAGE']);
}

// Vérifier si l'utilisateur est connecté
$isLoggedIn = isset($_SESSION['LOGGED_USER']);
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

    <div class="button-container">
        <?php if ($isLoggedIn): ?>
            <!-- Afficher les informations utilisateur et le bouton Déconnexion -->
            <p>Bienvenue, <?php echo htmlspecialchars($_SESSION['LOGGED_USER']['email']); ?> !</p>
            <button type="button" onclick="window.location.href='logout.php'">Déconnexion</button>
            <button id="btnPersonnalBest" type="button" onclick="window.location.href='main.php'">Vos Meilleurs Scores</button>
        <?php else: ?>
            <!-- Afficher les boutons pour les invités -->
            <button id="btnGuest" type="button" onclick="window.location.href='guestPage.php'">Jouer en mode invité</button>
            <button id="btnRegister" type="button" onclick="window.location.href='register.php'">Enregistre toi sur notre base de donnée HYPER sécurisée</button>
            <button type="button" onclick="window.location.href='login.php'">Se connecter</button>
        <?php endif; ?>
    </div>

    <script src="../site/script.js" defer></script>
</body>

</html>