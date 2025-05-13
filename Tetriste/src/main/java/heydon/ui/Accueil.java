package heydon.ui;

import static heydon.Tetriste.startGame;
import heydon.database.ScoreManager;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Accueil {
    
    // J'ai ajouté des commentaires pour les toutes les lignes afin de facilité la lecture

    // Création d'un bouton "Jouer"
    JButton jouerButton = new JButton("Jouer");

    // Création de la fenêtre principale
    JFrame frame = new JFrame("Jouer");

    // Création d'un panel personnalisé avec une image de fond
    JPanel panel = new BackgroundPanel("C:\\\\Users\\\\Victor\\\\Desktop\\\\ProjetBTSSIO_unzip\\\\LE_GOAT.jpg");

    // Label pour afficher le meilleur score
    JLabel labelBestScore = new JLabel();

    // Panel pour afficher le score (placé en haut de la fenêtre)
    JPanel scorePanel = new JPanel();

    // Informations de connexion à la base de données MySQL
    private static final String DB_URL = "jdbc:mysql://localhost:3306/tetriste";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";

    /**
     * Méthode statique pour mettre à jour un JLabel avec le meilleur score depuis la base de données.
     * @param label Le JLabel à mettre à jour
     */
    public static void updateLabelFromDatabase(JLabel label) {
        // Requête SQL pour récupérer les scores triés par ordre décroissant
        String query = "SELECT total_points FROM score ORDER BY total_points DESC;";
        try (
            // Connexion à la base de données
            Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            // Préparation de la requête
            PreparedStatement statement = connection.prepareStatement(query);
            // Exécution de la requête
            ResultSet resultSet = statement.executeQuery()
        ) {
            // Si un résultat est trouvé (le meilleur score)
            if (resultSet.next()) {
                String totalPoints = resultSet.getString("total_points");
                // Utilisation d'un ScoreManager pour récupérer le meilleur score
                ScoreManager scoreManager = new ScoreManager();
                label.setText("vos 5 meilleurs score sont " + scoreManager.getBestScore());
            }
        } catch (SQLException e) {
            // En cas d'erreur SQL, afficher un message d'erreur dans le label
            e.printStackTrace();
            label.setText("Erreur lors de la récupération des données");
        }
    }

    /**
     * Méthode pour initialiser et afficher la fenêtre d'accueil.
     * @param toto Un paramètre (probablement le nom du joueur ou autre)
     */
    public void AccueilFrame(String toto) {
        // Définir la taille de la fenêtre et du panel
        frame.setSize(500, 400);
        panel.setSize(500, 400);

        // Définir l'action à faire lors de la fermeture de la fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Utiliser BorderLayout pour organiser les composants dans la fenêtre
        frame.setLayout(new java.awt.BorderLayout());

        // Ajouter le panel principal (avec l'image de fond) au centre de la fenêtre
        frame.add(panel, BorderLayout.CENTER);

        // Rendre la fenêtre visible
        frame.setVisible(true);

        // Appeler la méthode qui place les éléments sur le panel d'accueil
        setupAcceuil();

        // Ajouter le panel du score en haut de la fenêtre
        frame.add(scorePanel, BorderLayout.NORTH);

        // Ajouter le label du meilleur score dans le scorePanel
        scorePanel.add(labelBestScore, BorderLayout.NORTH);

        // Mettre à jour le label du meilleur score à partir de la base de données
        updateLabelFromDatabase(labelBestScore);

        // Ajouter un écouteur d'événement sur le bouton "Jouer"
        jouerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Afficher un message dans la console (debug)
                System.out.println("Hello world");

                // Fermer la fenêtre d'accueil
                frame.dispose();

                // Lancer le jeu (méthode à définir ailleurs)
                JFrame gameFrame = startGame(toto);

                // Afficher la fenêtre du jeu
                gameFrame.setVisible(true);
            }
        });
    }

    /**
     * Méthode pour placer les éléments (bouton, label) sur le panel principal.
     */
    public void setupAcceuil() {
        // Utilisation de GridBagConstraints pour positionner les composants
        GridBagConstraints position = new GridBagConstraints();

        // Positionner le bouton "Jouer" avec des marges
        position.insets = new Insets(50, 0, 0, 0);
        position.gridwidth = 5;
        panel.add(jouerButton, position);

        // Positionner le label du meilleur score avec des marges différentes
        position.insets = new Insets(40, 0, 0, 0);
        position.gridwidth = 5;
        panel.add(labelBestScore, position);
    }

    /**
     * Classe interne personnalisée pour afficher une image de fond dans un JPanel.
     */
    static class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        // Constructeur : charge l'image de fond à partir d'un chemin
        public BackgroundPanel(String imagePath) {
            backgroundImage = Toolkit.getDefaultToolkit().getImage(imagePath);
        }

        // Redéfinition de la méthode paintComponent pour dessiner l'image en fond
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            // Dessiner l'image sur tout le panel
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}


