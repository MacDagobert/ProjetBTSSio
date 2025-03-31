
// Ici on fait un record_score.php

<?php
session_start(); // Assurez-vous de démarrer la session

// Vérifiez si l'utilisateur est connecté
if (!isset($_SESSION['LOGGED_USER'])) {
    echo json_encode(['message' => 'Utilisateur non connecté']);
    exit;
}

// Récupérer les données JSON envoyées
$data = json_decode(file_get_contents('php://input'), true);
$score = $data['score'];

// Récupérer l'ID de l'utilisateur connecté
$id_joueur = $_SESSION['LOGGED_USER']['id_joueur'];

try {
    // Connexion à la base de données (à adapter selon votre configuration)
    $pdo = new PDO('mysql:host=localhost;dbname=votre_base', 'utilisateur', 'mot_de_passe');
    $pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);

    // Préparer et exécuter la requête d'insertion
    $stmt = $pdo->prepare("INSERT INTO scores (id_joueur, score, date) VALUES (:id_joueur, :score, NOW())");
    $stmt->execute(['id_joueur' => $id_joueur, 'score' => $score]);

    // Renvoyer une réponse
    echo json_encode(['message' => 'Score enregistré avec succès']);

} catch (PDOException $e) {
    // En cas d'erreur, renvoyer un message
    echo json_encode(['message' => 'Erreur lors de l\'enregistrement du score: ' . $e->getMessage()]);
}
?>
