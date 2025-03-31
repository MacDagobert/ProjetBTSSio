<?php
session_start();
require_once(__DIR__ . '/config/mysql.php');
require_once(__DIR__ . '/databaseconnect.php');
require_once(__DIR__ . '/variables.php');

$postData = $_POST;

if (isset($postData['email']) && isset($postData['password'])) {
    $email = trim($postData['email']);
    $password = trim($postData['password']); // Ajout de trim() ici aussi

    error_log("Tentative de connexion pour l'email : " . $email);
    error_log("Mot de passe saisi : " . $password);
error_log("Résultat de password_verify : " . (password_verify($password, $user['password']) ? 'true' : 'false'));


    if (!filter_var($email, FILTER_VALIDATE_EMAIL)) {
        $_SESSION['LOGIN_ERROR_MESSAGE'] = 'Il faut un email valide pour soumettre le formulaire.';
        error_log("Email invalide : " . $email);
    } else {
        try {
            $sql = "SELECT * FROM joueurs WHERE email = ?";
            $stmt = $mysqlClient->prepare($sql);
            $stmt->execute([$email]);
            $user = $stmt->fetch();

            if ($user) {
                error_log("Utilisateur trouvé dans la base de données");
                error_log("Mot de passe haché en DB : " . $user['password']);
                
                if (password_verify($password, $user['password'])) {
                    $_SESSION['LOGGED_USER'] = [
                        'email' => $user['email'],
                        'id_joueur' => $user['id_joueur'],
                    ];
                    $_SESSION['SUCCESS_MESSAGE'] = 'Connexion réussie !';
                    error_log("Connexion réussie pour l'utilisateur : " . $email);
                } else {
                    $_SESSION['LOGIN_ERROR_MESSAGE'] = 'Mot de passe incorrect.';
                    error_log("Mot de passe incorrect pour l'email : " . $email);
                }
            } else {
                $_SESSION['LOGIN_ERROR_MESSAGE'] = 'Email non trouvé.';
                error_log("Aucun utilisateur trouvé pour l'email : " . $email);
            }
        } catch (PDOException $e) {
            $_SESSION['LOGIN_ERROR_MESSAGE'] = 'Une erreur est survenue. Veuillez réessayer plus tard.';
            error_log("Erreur de base de données : " . $e->getMessage());
        }
    }

    header('Location: main.php');
    exit;
}