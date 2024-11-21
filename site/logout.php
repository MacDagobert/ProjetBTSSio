<?php

session_start(); // Démarrez la session si ce n'est pas déjà fait

// Détruire la session
session_unset();
session_destroy();


header('Location: index.php');
exit;
?>