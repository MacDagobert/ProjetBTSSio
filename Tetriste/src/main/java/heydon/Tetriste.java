/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package heydon;

import javax.swing.*;


/**
 * Classe principale du jeu Tetris.
 * Cette classe initialise tous les composants nécessaires au fonctionnement du jeu,
 * configure l'interface graphique et lance la boucle principale.
 */
public class Tetriste {

    /**
     * Méthode principale qui démarre le jeu Tetris.
     *
     * @param args Les arguments de ligne de commande (non utilisés ici)
     */
    public static void main(String[] args) {
        // Création de la grille de jeu
        Grille grille = new Grille();

        // Création de l'objet Physics qui gère la logique du jeu
        Physics physics = new Physics(grille);

        // Création de l'objet Score qui gère le score du jeu
        Score score = new Score(grille);

        // Création du panneau graphique qui affiche le jeu
        GrillePanel panelJeu = new GrillePanel(grille, physics, score);

        // Création de l'objet Evenements qui gère les événements du jeu (clavier, timer)
        Evenements evenements = new Evenements(physics, score, panelJeu) {
            /**
             * Surcharge du timer pour mettre à jour les lignes complétées dans GrillePanel.
             */
          
        };

        // Connexion du panneau graphique à l'objet Physics
        physics.setTetrisPanel(panelJeu);

        // Création de la fenêtre principale du jeu
        JFrame frameJeu = new JFrame("Tetris");

        // Définition de l'action de fermeture de la fenêtre
        frameJeu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajout du panneau de jeu à la fenêtre
        frameJeu.add(panelJeu);

        // Ajustement automatique de la taille de la fenêtre en fonction des dimensions du panneau graphique
        frameJeu.pack();

        // Ajout de l'écouteur de clavier au panneau de jeu
        panelJeu.addKeyListener(evenements.getKeyAdapter());

        // Permet au panneau de jeu de recevoir le focus pour capter les événements clavier
        panelJeu.setFocusable(true);

        // Démarrage du timer qui gère la descente automatique des pièces
        evenements.start();

        // Affichage de la fenêtre de jeu
        frameJeu.setVisible(true);
    }
}