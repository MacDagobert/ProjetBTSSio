<?php
if (session_status() === PHP_SESSION_NONE) {
    session_start();
}
?>
<div class="header-container">
    <h1 class="titre">Ttrist</h1>
    <?php if (isset($_SESSION['LOGGED_USER'])) : ?>
        <?php
        // Check if LOGGED_USER is an array
        if (is_array($_SESSION['LOGGED_USER'])) {
            // Convert the array to a string in a meaningful way
            // For example, join the elements with a space
            $loggedUser = implode(' ', $_SESSION['LOGGED_USER']);
        } else {
            // If it's not an array, use it as is
            $loggedUser = $_SESSION['LOGGED_USER'];
        }
        ?>
        <div class="user-info">
            <div class="logged-user-name">
                <?php echo htmlspecialchars($loggedUser); ?>
            </div>
        </div>
        <ul class="nav-item">
            <a href="logout.php" class="nav-link logout-button">Déconnexion</a>
        </ul>
    <?php endif; ?>
</div>