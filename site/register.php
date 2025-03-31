<?php
session_start();
if (isset($_SESSION['REGISTER_ERROR_MESSAGE'])) {
    echo '<div class="alert alert-danger">' . $_SESSION['REGISTER_ERROR_MESSAGE'] . '</div>';
    unset($_SESSION['REGISTER_ERROR_MESSAGE']);
}
?>
<form action="submit_register.php" method="POST">
    <!-- si message d'erreur on l'affiche -->
    <?php if (isset($_SESSION['LOGIN_ERROR_MESSAGE'])) : ?>
        <div class="alert alert-danger" role="alert">
            <?php echo $_SESSION['LOGIN_ERROR_MESSAGE'];
            unset($_SESSION['LOGIN_ERROR_MESSAGE']); ?>
        </div>
    <?php endif; ?>
    <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input type="email" class="form-control" id="email" name="email" aria-describedby="email-help" placeholder="you@exemple.com">
        <div id="email-help" class="form-text">Votre email pour créer votre compte</div>
    </div>
    <div class="mb-3">
        <label for="password" class="form-label">Mot de passe</label>
        <input type="password" class="form-control" id="password" name="password">
    </div>
    <div class="mb-3">
        <label for="password_repeat" class="form-label">Répétez votre mot de passe</label>
        <input type="password" class="form-control" id="password_repeat" name="password_repeat">
    </div>
    <div class="mb-3">
    <label for="pseudo" class="form-label">Pseudo</label>
    <input type="text" class="form-control" id="pseudo" name="pseudo" aria-describedby="pseudo-help" placeholder="Votre pseudo">
    <div id="pseudo-help" class="form-text">Choisissez un pseudo pour votre compte</div>
</div>

<input type="hidden" name="date_inscription" value="<?php echo date('Y-m-d'); ?>">
    <button type="submit" class="btn btn-primary">Envoyer</button>
</form>
<script>
document.querySelector('form').addEventListener('submit', function(event) {
    const password = document.getElementById('password').value;
    const passwordRepeat = document.getElementById('password_repeat').value;

    if (password !== passwordRepeat) {
        event.preventDefault(); // Empêche l'envoi du formulaire
        alert("Les mots de passe ne correspondent pas. Veuillez réessayer.");
    }
});
</script>