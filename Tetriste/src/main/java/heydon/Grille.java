/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heydon;

/**
 * Classe Grille représentant la grille du jeu Tetris.
 * Elle gère l'état des cellules, la suppression des lignes complètes et la réinitialisation de la grille.
 */
public class Grille {
    // Constantes définissant la taille de la grille et des blocs
    private static final int GRID_WIDTH = 10;  // Nombre de colonnes dans la grille
    private static final int GRID_HEIGHT = 20; // Nombre de lignes dans la grille
    private static final int BLOCK_SIZE = 30;  // Taille d'un bloc en pixels (pour l'affichage)

    // Tableau 2D représentant l'état de la grille
    // Chaque cellule est `true` si elle est occupée, `false` sinon
    private boolean[][] grid = new boolean[GRID_HEIGHT][GRID_WIDTH];

    /**
     * Retourne le nombre de colonnes dans la grille.
     *
     * @return Le nombre de colonnes (largeur de la grille)
     */
    public int getGridWidth() {
        return GRID_WIDTH;
    }

    /**
     * Retourne le nombre de lignes dans la grille.
     *
     * @return Le nombre de lignes (hauteur de la grille)
     */
    public int getGridHeight() {
        return GRID_HEIGHT;
    }

    /**
     * Retourne la taille d'un bloc en pixels.
     * Cette valeur est utilisée pour l'affichage graphique.
     *
     * @return La taille d'un bloc en pixels
     */
    public int getBlockSize() {
        return BLOCK_SIZE;
    }

    /**
     * Vérifie si une cellule spécifique (ligne, colonne) est occupée.
     *
     * @param row La ligne de la cellule
     * @param col La colonne de la cellule
     * @return true si la cellule est occupée, false sinon
     */
    public boolean isCellOccupied(int row, int col) {
        return grid[row][col];
    }

    /**
     * Définit l'état d'une cellule spécifique (occupée ou non).
     *
     * @param row      La ligne de la cellule
     * @param col      La colonne de la cellule
     * @param occupied true pour marquer la cellule comme occupée, false pour la libérer
     */
    public void setCellOccupied(int row, int col, boolean occupied) {
        grid[row][col] = occupied;
    }

    /**
     * Efface une ligne complète et fait descendre toutes les lignes au-dessus.
     *
     * @param row La ligne à effacer (complète)
     */
    public void clearLine(int row) {
        // Parcourt toutes les lignes au-dessus de la ligne donnée, en partant du bas
        for (int y = row; y > 0; y--) {
            for (int x = 0; x < GRID_WIDTH; x++) {
                // Déplace chaque cellule de la ligne au-dessus vers la ligne actuelle
                grid[y][x] = grid[y - 1][x];
            }
        }
        // Vide la première ligne (en haut de la grille)
        for (int x = 0; x < GRID_WIDTH; x++) {
            grid[0][x] = false;
        }
    }

    /**
     * Réinitialise toute la grille en mettant toutes les cellules à `false` (vide).
     */
    public void reset() {
        for (int y = 0; y < GRID_HEIGHT; y++) { // Parcourt toutes les lignes
            for (int x = 0; x < GRID_WIDTH; x++) { // Parcourt toutes les colonnes
                grid[y][x] = false; // Vide chaque cellule
            }
        }
    }
}