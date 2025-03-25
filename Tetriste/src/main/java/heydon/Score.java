/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heydon;

import heydon.database.ScoreManager;

/**
 * Classe Score qui gère le score du jeu Tetris.
 * Cette classe est responsable de l'incrémentation du score en fonction des lignes complètes
 * et fournit des méthodes pour récupérer et réinitialiser le score.
 */
public class Score {

    private int score; // Score actuel
    private Grille grille; // Référence à l'objet Grille pour vérifier les lignes complètes
    private final ScoreManager scoreManager; // Pour sauvegarder les scores en base de données

    /**
     * Constructeur de la classe Score. Initialise le score à 0 et associe une
     * grille pour vérifier l'état des lignes.
     *
     * @param grille L'objet Grille représentant la grille du jeu
     */
    public Score(Grille grille, ScoreManager scoreManager) {
        this.grille = grille;
        this.score = 0; // Initialiser le score à 0
        this.scoreManager = scoreManager;
    }

    /**
     * Définit une nouvelle valeur pour le score.
     *
     * @param score La nouvelle valeur du score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Vérifie les lignes complètes dans la grille, les supprime si nécessaire,
     * et ajoute des points au score en fonction du nombre de lignes supprimées.
     *
     * @return Le nombre de lignes supprimées lors de cet appel
     */
    public int winpoint() {
        int gridHeight = grille.getGridHeight(); // Hauteur de la grille
        int gridWidth = grille.getGridWidth(); // Largeur de la grille

        int linesCleared = 0; // Compteur pour le nombre de lignes supprimées

        // Parcourir toutes les lignes de la grille, de bas en haut
        for (int y = gridHeight - 1; y >= 0; y--) {
            boolean ligneRemplie = true;

            // Vérifier chaque cellule de la ligne actuelle
            for (int x = 0; x < gridWidth; x++) {
                if (!grille.isCellOccupied(y, x)) { // Si une cellule n'est pas occupée, la ligne n'est pas remplie
                    ligneRemplie = false;
                    break;
                }
            }

            // Si la ligne est remplie, on la supprime
            if (ligneRemplie) {
                System.out.println("Ligne remplie!");
                grille.clearLine(y); // Effacer la ligne complète
                linesCleared++; // Incrémenter le compteur de lignes supprimées
                y++; // Re-vérifier cette ligne après avoir déplacé les lignes au-dessus
            }
        }

        // Ajouter des points en fonction du nombre de lignes supprimées
        int oldScore = score;
        switch (linesCleared) {
            case 1:
                score += 100; // 100 points pour une seule ligne
                break;
            case 2:
                score += 300; // 200 points pour deux lignes + 100 bonus
                break;
            case 3:
                score += 600; // 300 points pour trois lignes + 300 bonus
                break;
            case 4:
                score += 1000; // 400 points pour quatre lignes + 600 bonus
                break;
            default:
                break; // Aucun point si aucune ligne n'a été supprimée
        }
        
        // Met à jour le score dans le ScoreManager
        if (score > oldScore) {
            scoreManager.updateCurrentScore(score);
        }

        return linesCleared; // Retourne le nombre total de lignes supprimées
    }

    /**
     * Retourne le score actuel.
     *
     * @return Le score actuel sous forme d'entier
     */
    public int getScore() {
        return score;
    }

    /**
     * Réinitialise le score à 0.
     */
    public void resetScore() {
        this.score = 0;
        scoreManager.updateCurrentScore(0);
    }
    
    /**
     * Sauvegarde le score final dans la base de données.
     */
    public void saveScore() {
        scoreManager.saveScore();
    }
}