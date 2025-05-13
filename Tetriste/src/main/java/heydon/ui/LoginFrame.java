package heydon.ui;

import heydon.database.DatabaseConnection;
import heydon.database.ScoreManager;
import heydon.Tetriste;
import heydon.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/**
 * Fenêtre de connexion pour le jeu Tetris.
 */
// Classe principale de la fenêtre de connexion
public class LoginFrame extends JFrame {
    
    // J'ai ajouté des commentaires pour les toutes les lignes afin de facilité la lecture

    // Déclaration des champs pour la connexion et l'inscription
    private JTextField pseudoField;            // Champ pour le pseudo lors de la connexion
    private JPasswordField passwordField;      // Champ pour le mot de passe lors de la connexion
    private JTextField newPseudoField;         // Champ pour le nouveau pseudo lors de l'inscription
    private JPasswordField newPasswordField;   // Champ pour le nouveau mot de passe lors de l'inscription
    private JPasswordField confirmPassword;    // Champ pour confirmer le mot de passe lors de l'inscription
    private JTextField emailField;             // Champ pour l'email lors de l'inscription
    private JButton loginButton;               // Bouton pour se connecter
    private JButton newRegisterButton;         // Bouton pour valider l'inscription
    private JButton registerButton;            // Bouton pour afficher le formulaire d'inscription
    private JButton backButton;                // Bouton pour revenir à la connexion
    private final DatabaseConnection dbConnection; // Instance de connexion à la base de données
    private final ScoreManager scoreManager;       // Gestionnaire de score
    public String currentPseudo;                   // Stocke le pseudo du joueur connecté

    // Labels pour les champs du formulaire
    JLabel newPasswordLabel = new JLabel("Nouveau Mot de passe :");
    JLabel confirmPasswordLabel = new JLabel("Confirmer le Mot de passe :");
    JLabel passwordLabel = new JLabel("Mot de passe :");
    JLabel newPseudoLabel = new JLabel("Pseudo :");
    JLabel pseudoLabel = new JLabel("Pseudo :");
    JLabel emailLabel = new JLabel("email :");

    // Constructeur de la fenêtre de connexion
    public LoginFrame() {
        this.dbConnection = DatabaseConnection.getInstance(); // Récupère l'instance de connexion BDD
        this.scoreManager = new ScoreManager();               // Initialise le gestionnaire de score

        setupUI(); // Configure l'interface utilisateur
    }

    // Méthode pour configurer l'interface graphique
    private void setupUI() {
        setTitle("Tetriste - Connexion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Centre la fenêtre

        // Création du panel principal avec un layout flexible
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Marge autour des composants

        // Titre de la fenêtre
        JLabel titleLabel = new JLabel("TETRISTE");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = -1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);

        // Ajout du label pseudo pour la connexion
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(pseudoLabel, gbc);
        pseudoLabel.setVisible(true);

        // Ajout du label pseudo pour l'inscription (caché par défaut)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(newPseudoLabel, gbc);
        newPseudoLabel.setVisible(false);

        // Ajout du label email (caché par défaut)
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(emailLabel, gbc);

        // Champ pour le pseudo (connexion)
        pseudoField = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(pseudoField, gbc);

        // Label pour confirmation du mot de passe (caché par défaut)
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(confirmPasswordLabel, gbc);
        confirmPasswordLabel.setVisible(false);

        // Champ pour le nouveau pseudo (inscription)
        newPseudoField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(newPseudoField, gbc);
        newPseudoField.setVisible(false);

        // Champ pour le nouveau mot de passe (inscription)
        newPasswordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(newPasswordField, gbc);
        newPasswordField.setVisible(false);

        // Label pour le mot de passe (connexion)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(passwordLabel, gbc);

        // Label pour le nouveau mot de passe (inscription, caché par défaut)
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(newPasswordLabel, gbc);
        newPasswordLabel.setVisible(false);

        // Champ pour confirmer le mot de passe (inscription)
        confirmPassword = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(confirmPassword, gbc);
        confirmPassword.setVisible(false);

        // Champ pour le mot de passe (connexion)
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(passwordField, gbc);

        // Champ pour l'email (inscription)
        emailField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(emailField, gbc);
        emailField.setVisible(false);

        // Cache le label email au départ
        emailLabel.setVisible(false);

        // Bouton de connexion
        loginButton = new JButton("Se connecter");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);

        // Bouton pour afficher le formulaire d'inscription
        registerButton = new JButton("s'inscrire");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(registerButton, gbc);
        registerButton.setVisible(true);

        // Bouton pour valider l'inscription (caché par défaut)
        newRegisterButton = new JButton("S'enregistrer");
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(newRegisterButton, gbc);
        newRegisterButton.setVisible(false);

        // Bouton retour (caché par défaut)
        backButton = new JButton("retour");
        gbc.gridx = 2;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        mainPanel.add(backButton, gbc);
        backButton.setVisible(false);

        // Ajout des actions sur les boutons
        loginButton.addActionListener(e -> handleLogin());
        registerButton.addActionListener(e -> newHandLeRegister());
        newRegisterButton.addActionListener(e -> handleRegister());
        backButton.addActionListener(e -> back());

        // Ajoute le panel principal à la fenêtre
        add(mainPanel);

        // Permet la connexion avec la touche Entrée
        getRootPane().setDefaultButton(loginButton);
    }

    // Affiche le formulaire d'inscription et masque le formulaire de connexion
    private void newHandLeRegister() {
        // Masque les éléments de connexion
        loginButton.setVisible(false);
        passwordField.setVisible(false);
        pseudoField.setVisible(false);
        registerButton.setVisible(false);
        passwordLabel.setVisible(false);
        pseudoLabel.setVisible(false);

        // Modifie les labels pour l'inscription
        newPseudoLabel.setText("Nouveau Pseudo");
        emailLabel.setText("email :");

        // Affiche les éléments pour l'inscription
        newPseudoField.setVisible(true);
        newPasswordField.setVisible(true);
        newRegisterButton.setVisible(true);
        emailField.setVisible(true);
        emailLabel.setVisible(true);
        newPasswordLabel.setVisible(true);
        newPseudoLabel.setVisible(true);
        backButton.setVisible(true);
        confirmPasswordLabel.setVisible(true);
        confirmPassword.setVisible(true);
    }

    // Revient à l'écran de connexion depuis l'inscription
    private void back() {
        // Affiche les éléments de connexion
        loginButton.setVisible(true);
        passwordField.setVisible(true);
        pseudoField.setVisible(true);
        registerButton.setVisible(true);
        passwordLabel.setVisible(true);
        pseudoLabel.setVisible(true);

        // Masque les éléments d'inscription
        newPseudoField.setVisible(false);
        newPasswordField.setVisible(false);
        newRegisterButton.setVisible(false);
        emailField.setVisible(false);
        emailLabel.setVisible(false);
        newPasswordLabel.setVisible(false);
        newPseudoLabel.setVisible(false);
        backButton.setVisible(false);
        confirmPasswordLabel.setVisible(false);
        confirmPassword.setVisible(false);
    }

    // Gère la connexion de l'utilisateur
    private void handleLogin() {
        String pseudo = pseudoField.getText();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(this.confirmPassword.getPassword());

        // Vérifie que les champs ne sont pas vides
        if (pseudo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez remplir tous les champs",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Vérifie les identifiants dans la base
            if (authenticatePlayer(pseudo, password)) {
                currentPseudo = pseudo;  // Stocke le pseudo du joueur connecté
                scoreManager.setPlayerPseudo(pseudo); // Met à jour le pseudo dans le gestionnaire de score
                startGame(); // Lance le jeu
            } else {
                JOptionPane.showMessageDialog(this,
                        "Pseudo ou mot de passe incorrect",
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur de connexion à la base de données: " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Gère l'inscription d'un nouvel utilisateur
    public void handleRegister() {
        String newMail = emailField.getText();
        String newPseudo = newPseudoField.getText();
        String newPassword = new String(newPasswordField.getPassword());
        String confirmpassword = new String(confirmPassword.getPassword());
        back(); // Revient à l'écran de connexion après inscription

        // Vérifie que les deux mots de passe sont identiques
        if (!(newPassword.compareTo(confirmpassword) == 0)) {
            JOptionPane.showMessageDialog(this,
                    "Les mots de passe rentrés ne sont pas les mêmes.",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Vérifie que les champs ne sont pas vides
        if (newPseudo.isEmpty() || newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Veuillez remplir tous les champs",
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Génère un sel et hache le mot de passe avec BCrypt
        String salt = BCrypt.gensalt();
        String hashedPassword = BCrypt.hashpw(newPassword, salt);

        // Affiche le mot de passe haché (pour debug)
        System.out.println("Hashed Password: " + hashedPassword);

        // Prépare la requête SQL pour insérer le nouvel utilisateur
        String sql = "INSERT INTO joueurs (pseudo, email, password) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tetriste", "root", "");
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Définit les paramètres de la requête
            pstmt.setString(1, newPseudo);
            pstmt.setString(2, newMail);
            pstmt.setString(3, hashedPassword);

            // Exécute la requête d'insertion
            int rowsAffected = pstmt.executeUpdate();
            JOptionPane.showMessageDialog(this, rowsAffected + " ligne(s) insérée(s).");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this,
                    "Erreur de connexion à la base de données: " + ex.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    // Vérifie les identifiants de l'utilisateur dans la base de données
    private boolean authenticatePlayer(String pseudo, String password) throws SQLException {
        String sql = "SELECT password FROM joueurs WHERE pseudo = ?";

        try (Connection conn = dbConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, pseudo);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    // Récupère le mot de passe haché depuis la base
                    String hashedPassword = rs.getString("password");
                    System.out.println("Mot de passe haché récupéré : " + hashedPassword);

                    // Vérifie si le mot de passe saisi correspond au hachage
                    return BCrypt.checkpw(password, hashedPassword);
                }
            }
        }
        return false;
    }

    // Lance le jeu après une connexion réussie
    private void startGame() {
        this.dispose(); // Ferme la fenêtre de connexion
        SwingUtilities.invokeLater(() -> {
            try {
                // Lance la fenêtre d'accueil du jeu avec le pseudo du joueur
                Accueil accueil = new Accueil();
                accueil.AccueilFrame(currentPseudo);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Erreur lors du lancement du jeu: " + e.getMessage(),
                        "Erreur",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    // Méthode principale pour lancer l'application
    public static void main(String[] args) {
        // Assure que l'interface graphique est créée dans le thread de Swing
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                LoginFrame lf = new LoginFrame();
                lf.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
