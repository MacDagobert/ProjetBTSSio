<?php
session_start();
//Atention requeteSQL  a modifier  tOTALpOINT + A SCORE
// Vérifiez si l'utilisateur est connecté
if (!isset($_SESSION['LOGGED_USER'])) {
    echo json_encode(['message' => 'Utilisateur non connecté']);
    exit;
}

// Récupérer les données JSON envoyées
$data = json_decode(file_get_contents('php://input'), true);
$total_points = $data['score'];

// Récupérer l'ID de l'utilisateur connecté
$id_joueur = $_SESSION['LOGGED_USER']['id_joueur'];

// Inclure les fichiers de configuration et de connexion à la base de données
require_once(__DIR__ . '/config/mysql.php');
require_once(__DIR__ . '/databaseconnect.php');
require_once(__DIR__ . '/variables.php');

try {
    // 1. Créer une nouvelle partie
    $sqlPartie = "INSERT INTO partie (id_joueur, date_partie, temps_partie, type_partie) 
                    VALUES (:id_joueur, NOW(), '', 'Tetris')";
    $stmtPartie = $mysqlClient->prepare($sqlPartie);
    $stmtPartie->execute(['id_joueur' => $id_joueur]);

    // Récupérer l'ID de la partie nouvellement créée
    $id_partie = $mysqlClient->lastInsertId();
    error_log("ID de la partie créée : " . $id_partie);

    // 2. Enregistrer le score
    error_log("Score reçu : " . $total_points);
    $sqlScore = "INSERT INTO score (id_partie, total_points, niveau_max) 
                   VALUES (:id_partie, :score, 1)";
    $stmtScore = $mysqlClient->prepare($sqlScore);
    $stmtScore->execute(['id_partie' => $id_partie, 'score' => $total_points]);

    // Renvoyer une réponse
    echo json_encode(['message' => 'Score enregistré avec succès']);

} catch (PDOException $e) {
    // En cas d'erreur, renvoyer un message
    error_log("Erreur lors de l'enregistrement du score: " . $e->getMessage());
    echo json_encode(['message' => 'Erreur lors de l\'enregistrement du score: ' . $e->getMessage()]);
}