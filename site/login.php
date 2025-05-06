<?php
session_start();

// Vérifie si l'utilisateur est déjà connecté
if (isset($_SESSION['LOGGED_USER'])) {
    $_SESSION['ERROR_MESSAGE'] = "Vous êtes déjà connecté.";
    header('Location: main.php');
    exit;
}
?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Connexion</title>
    <link rel="stylesheet" href="../Site/style.css">
</head>
<body>


    <?php
    // Affiche les messages d'erreur si présents
    if (isset($_SESSION['ERROR_MESSAGE'])) {
        echo '<div class="alert alert-danger" role="alert">';
        echo htmlspecialchars($_SESSION['ERROR_MESSAGE']);
        echo '</div>';
        unset($_SESSION['ERROR_MESSAGE']);
    }
    ?>

    <form action="submit_login.php" method="POST">
    <div class="container1">
    <h2>Connexion</h2>
        <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input type="email" class="form-control" id="email" name="email" aria-describedby="email-help" placeholder="you@exemple.com" required>
            <div id="email-help" class="form-text">L'email utilisé lors de la création de compte.</div>
        </div>
        <div class="mb-3">
            <label for="password" class="form-label">Mot de passe</label>
            <input type="password" class="form-control" id="password" name="password" required>
        </div>
        <button type="submit" class="button">Se connecter</button>
        </div>
    </form>
</body>
</html>