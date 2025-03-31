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
public class LoginFrame extends JFrame {
    private JTextField pseudoField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private final DatabaseConnection dbConnection;
    private final ScoreManager scoreManager;
    private String currentPseudo;  // Stocke le pseudo du joueur connecté
  
    
    public LoginFrame() {
        this.dbConnection = DatabaseConnection.getInstance();
        this.scoreManager = new ScoreManager();
        
        setupUI();
    }
    
    private void setupUI() {
        setTitle("Tetris - Connexion");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);
        
        // Panel principal avec GridBagLayout
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Titre
        JLabel titleLabel = new JLabel("TETRIS");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(titleLabel, gbc);
        
        // Pseudo
        JLabel pseudoLabel = new JLabel("Pseudo :");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(pseudoLabel, gbc);
        
        pseudoField = new JTextField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(pseudoField, gbc);
        
        // Mot de passe
        JLabel passwordLabel = new JLabel("Mot de passe :");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(passwordLabel, gbc);
        
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(passwordField, gbc);
        
        // Bouton de connexion
        loginButton = new JButton("Se connecter");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);
        
        // Action du bouton
        loginButton.addActionListener(e -> handleLogin());
        
        // Ajoute le panel principal
        add(mainPanel);
        
        // Permet la connexion avec la touche Entrée
        getRootPane().setDefaultButton(loginButton);
    }
    
    private void handleLogin() {
        String pseudo = pseudoField.getText();
        String password = new String(passwordField.getPassword());
        
        if (pseudo.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Veuillez remplir tous les champs",
                "Erreur",
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            if (authenticatePlayer(pseudo, password)) {
                currentPseudo = pseudo;  // Stocke le pseudo
                scoreManager.setPlayerPseudo(pseudo);
                startGame();
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
    
    return false; // Retourne false si aucun utilisateur n'est trouvé
}
    private void startGame() {
        this.dispose(); // Ferme la fenêtre de connexion
        SwingUtilities.invokeLater(() -> {
            try {
                // Lance le jeu avec le pseudo du joueur
                JFrame gameFrame = Tetriste.startGame(currentPseudo);
                gameFrame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                    "Erreur lors du lancement du jeu: " + e.getMessage(),
                    "Erreur",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
    
    public static void main(String[] args) {
        // Assure que l'interface est créée dans l'EDT
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                new LoginFrame().setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}