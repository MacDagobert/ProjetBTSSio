<?php

session_start();
require_once(__DIR__ . '/config/mysql.php');
require_once(__DIR__ . '/databaseconnect.php');
require_once(__DIR__ . '/variables.php');

$postData = $_POST;

//Valider les champs
if (empty($postData['email']) || empty($postData['password']) || empty($postData['password_repeat']) || empty($postData['pseudo']) || empty($postData['date_inscription'])) {
    $_SESSION['REGISTER_ERROR_MESSAGE'] = 'Tous les champs sont requis.';
    header('Location: register.php');
    exit;
}

/* Validation du formulaire */
if (isset($postData['email']) && isset($postData['password']) && isset($postData['password_repeat']) && isset($postData['pseudo']) && isset($postData['date_inscription'])) {
    if (!filter_var($postData['email'], FILTER_VALIDATE_EMAIL)) {
        $_SESSION['REGISTER_ERROR_MESSAGE'] = 'Il faut un email valide pour soumettre le formulaire.';
    } elseif ($postData['password'] !== $postData['password_repeat']) {
        $_SESSION['REGISTER_ERROR_MESSAGE'] = 'Les mots de passe ne correspondent pas.';
    } elseif (empty($postData['pseudo'])) {
        $_SESSION['REGISTER_ERROR_MESSAGE'] = 'Le pseudo ne peut pas être vide.';
    } else {
        // Vérifier si l'email existe déjà dans la base de données
        $query = "SELECT * FROM joueurs WHERE email = ?";
        $stmt = $mysqlClient->prepare($query);
        $stmt->execute([$postData['email']]);
        
        if ($stmt->rowCount() > 0) {
            $_SESSION['REGISTER_ERROR_MESSAGE'] = 'Cet email est déjà utilisé.';
            header('Location: register.php');
            exit;
        } else {
            // Insérer le nouvel utilisateur dans la base de données
            $query = "INSERT INTO joueurs (email, password, pseudo, date_inscription) VALUES (?, ?, ?, ?)";
            $stmt = $mysqlClient->prepare($query);
            $hashedPassword = password_hash($postData['password'], PASSWORD_DEFAULT);
            
            if ($stmt->execute([$postData['email'], $hashedPassword, $postData['pseudo'], $postData['date_inscription']])) {
                $_SESSION['LOGGED_USER'] = [
                    'email' => $postData['email'],
                    'user_id' => $mysqlClient->lastInsertId(),
                    'pseudo' => $postData['pseudo'],
                ];
                $_SESSION['REGISTER_SUCCESS_MESSAGE'] = 'Inscription réussie !';
                header('Location: index.php');
                exit;
            } else {
                $_SESSION['REGISTER_ERROR_MESSAGE'] = 'Erreur lors de l\'inscription.';
                header('Location: register.php');
                exit;
            }
        }
    }
} else {
    $_SESSION['REGISTER_ERROR_MESSAGE'] = 'Tous les champs sont requis.';
    header('Location: register.php');
    exit;
}