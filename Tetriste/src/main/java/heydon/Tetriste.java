package heydon;

import javax.swing.*;
import heydon.database.ScoreManager;

/**
 * Classe principale du jeu Tetris.
 * Cette classe initialise tous les composants nécessaires au fonctionnement du jeu,
 * configure l'interface graphique et lance la boucle principale.
 */
public class Tetriste {
    
    /**
     * Démarre une nouvelle partie de Tetris.
     * @param playerName Le nom du joueur
     * @return La fenêtre du jeu créée
     */
    public static JFrame startGame(String playerName) {
        // Création de la grille de jeu
        Grille grille = new Grille();

        // Création de l'objet Physics qui gère la logique du jeu
        Physics physics = new Physics(grille);

        // Création du gestionnaire de score
        ScoreManager scoreManager = new ScoreManager();
        scoreManager.setPlayerPseudo(playerName);

        // Création de l'objet Score qui gère le score du jeu
        Score score = new Score(grille, scoreManager);

        // Création du panneau graphique qui affiche le jeu
        GrillePanel panelJeu = new GrillePanel(grille, physics, score);

        // Création de l'objet Evenements qui gère les événements du jeu (clavier, timer)
        Evenements evenements = new Evenements(physics, score, panelJeu);

        // Connexion du panneau graphique à l'objet Physics
        physics.setTetrisPanel(panelJeu);

        // Création de la fenêtre principale du jeu
        JFrame frameJeu = new JFrame("Tetris - Joueur: " + playerName);

        // Définition de l'action de fermeture de la fenêtre
        frameJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajout du panneau de jeu à la fenêtre
        frameJeu.add(panelJeu);

        // Ajustement automatique de la taille de la fenêtre
        frameJeu.pack();

        // Centre la fenêtre
        frameJeu.setLocationRelativeTo(null);

        // Ajout de l'écouteur de clavier
        panelJeu.addKeyListener(evenements.getKeyAdapter());
        panelJeu.setFocusable(true);

        // Démarrage du timer
        evenements.start();
        
        return frameJeu;
    }

    /**
     * Point d'entrée principal du jeu.
     * @param args Les arguments de ligne de commande (non utilisés)
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame gameFrame = startGame("Joueur");
            gameFrame.setVisible(true);
        });
    }
}