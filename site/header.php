


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
        <ul class="nav-item">
        <a href="logout.php" class="nav-link logout-button">Déconnexion</a>
    </ul>
    </div>
        <p class="conexok">Bienvenue, <?php echo htmlspecialchars($loggedUser); ?></p>
        
    <?php endif; ?>
</div>
