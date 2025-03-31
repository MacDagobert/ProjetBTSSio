<?php
session_start();
require_once(__DIR__ . '/config/mysql.php');
require_once(__DIR__ . '/databaseconnect.php');
require_once(__DIR__ . '/variables.php');

// Vérification de la connexion PDO
if (!$mysqlClient) {
    die("Erreur de connexion à la base de données");
}

// Vérification que l'utilisateur est bien connecté
if (!isset($_SESSION['LOGGED_USER'])) {
    // Ajoute un message de notification ou d'erreur dans la session
    $_SESSION['ERROR_MESSAGE'] = "Vous devez être connecté pour accéder à cette page.";
    
    // Redirection vers index.php
    header('Location: index.php');
    exit; // Termine l'exécution du script pour éviter tout traitement supplémentaire
}



$topScores = [];

// Vérification de la session utilisateur
if (isset($_SESSION['LOGGED_USER'])) {
    try {
        $userEmail = is_array($_SESSION['LOGGED_USER']) ? $_SESSION['LOGGED_USER']['email'] : $_SESSION['LOGGED_USER'];

        $queryUser = "SELECT id_joueur FROM joueurs WHERE email = ?";
        $stmtUser = $mysqlClient->prepare($queryUser);
        $stmtUser->execute([$userEmail]);
        $user = $stmtUser->fetch(PDO::FETCH_ASSOC);

        if ($user && isset($user['id_joueur'])) {
            $userId = $user['id_joueur'];
            $query = "SELECT s.total_points, s.niveau_max 
                     FROM score s
                     JOIN partie p ON s.id_partie = p.id_partie
                     JOIN joueurs j ON p.id_joueur = j.id_joueur
                     WHERE j.id_joueur = ?
                     ORDER BY s.total_points DESC
                     LIMIT 5";

            $stmt = $mysqlClient->prepare($query);
            $stmt->execute([$userId]);
            $topScores = $stmt->fetchAll(PDO::FETCH_ASSOC);
        }
    } catch (PDOException $e) {
        error_log("Erreur SQL: " . $e->getMessage());
    }
}

// Vérifie et affiche un message d'erreur si présent
$errorMessage = null;
if (isset($_SESSION['ERROR_MESSAGE'])) {
    $errorMessage = $_SESSION['ERROR_MESSAGE'];
    unset($_SESSION['ERROR_MESSAGE']); // Supprime le message après affichage
}
?>

<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../Site/style.css">
    <title>Top Scores</title>
</head>
<body>
    <?php require_once(__DIR__ . '/header.php'); ?>

    <?php if ($errorMessage): ?>
        <div class="alert alert-danger" role="alert">
            <?= htmlspecialchars($errorMessage) ?>
        </div>
    <?php endif; ?>

    <div id="topScores">
        <h2>Vos 5 meilleurs scores</h2>
        <?php if (!empty($topScores)): ?>
            <ul>
                <?php foreach ($topScores as $score): ?>
                    <li>Score : <?= htmlspecialchars((string)$score['total_points']) ?>, Niveau : <?= htmlspecialchars((string)$score['niveau_max']) ?></li>
                <?php endforeach; ?>
            </ul>
        <?php else: ?>
            <p>Aucun score disponible.</p>
        <?php endif; ?>
    </div>

    <div class="button-container">
        <!-- Bouton Jouer -->
        <button id="btnPlay" type="button" onclick="window.location.href='guestPage.php'">Joue !</button>
    </div>

    <script src="../site/script.js" defer></script>
</body>
</html>