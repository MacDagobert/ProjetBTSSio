/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heydon;

import javax.swing.*;
import java.awt.event.*;

/**
 * Classe Evenements qui gère les événements du jeu Tetris.
 * Cette classe s'occupe des interactions utilisateur via le clavier et du timer pour le déplacement automatique des tetrominos.
 */
public class Evenements {
    private Physics physics;
    private Score score;
    private Timer timer;
    private GrillePanel panelJeu;
    private int lignesEffectuees;

    /**
     * Constructeur de la classe Evenements.
     * Initialise la logique du jeu, le score, le panneau de jeu et configure un timer pour déplacer automatiquement les tetrominos.
     *
     * @param physics L'objet Physics qui gère la logique du jeu
     * @param score   L'objet Score qui gère les points
     * @param panelJeu Le panneau de jeu pour l'affichage graphique
     */
    public Evenements(Physics physics, Score score, GrillePanel panelJeu) {
        this.physics = physics;
        this.score = score;
        this.panelJeu = panelJeu;
        this.lignesEffectuees = 0;

        timer = new Timer(500, new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (physics.isGameOver()) {
                    gameOver();
                    return;
                }
        physics.moveTetrominoDown();
        int lignesSupprimees = score.winpoint(); // Vérifie et met à jour le score en fonction des lignes complètes
        if (lignesSupprimees > 0) {
            System.out.println("Lignes supprimées : " + lignesSupprimees); // Message de débogage
            lignesEffectuees += lignesSupprimees; // Met à jour le compteur local
            panelJeu.ajouterLignesEffectuees(lignesSupprimees); // Met à jour les lignes dans GrillePanel
            ajusterVitesseTimer(); // Ajuste la vitesse en fonction du niveau
        }
        panelJeu.repaint(); // Redessine le panneau pour afficher les changements
    }
});
    }
    private void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(panelJeu, "Game Over! Votre score : " + score.getScore(), 
                                      "Game Over", 
                                      JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Ajuste la vitesse du timer en fonction du niveau actuel.
     */
    private void ajusterVitesseTimer() {
        int niveau = (lignesEffectuees / 20) + 1;
        int nouveauDelai = Math.max(100, 500 - (niveau - 1) * 50);
        timer.setDelay(nouveauDelai);
    }

    /**
     * Démarre le timer pour faire descendre automatiquement les tetrominos.
     */
    public void start() {
        timer.start();
    }

    /**
     * Arrête le timer pour suspendre le déplacement automatique des tetrominos.
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Retourne un KeyAdapter qui gère les interactions clavier pour contrôler les tetrominos.
     *
     * @return Un KeyAdapter configuré pour répondre aux touches directionnelles
     */
    public KeyAdapter getKeyAdapter() {
        return new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        physics.moveTetrominoLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        physics.moveTetrominoRight();
                        break;
                    case KeyEvent.VK_DOWN:
                        physics.moveTetrominoDown();
                        break;
                    case KeyEvent.VK_UP:
                        physics.rotateTetromino();
                        break;
                }
                panelJeu.repaint();
            }
        };
    }
}