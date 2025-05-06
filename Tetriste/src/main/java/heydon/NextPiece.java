/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heydon;

/**
 *
 * @author Victor
 */
public class NextPiece {
    private static final int GRID_WIDTH = 10;  // Nombre de colonnes dans la grille
    private static final int GRID_HEIGHT = 60; // Nombre de lignes dans la grille
    private static final int BLOCK_SIZE = 30;  // Taille d'un bloc en pixels (pour l'affichage)

    // Tableau 2D représentant l'état de la grille
    // Chaque cellule est `true` si elle est occupée, `false` sinon
    private boolean[][] grid = new boolean[GRID_HEIGHT][GRID_WIDTH];
    
    
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

    public static int getGRID_WIDTH() {
        return GRID_WIDTH;
    }

    public static int getGRID_HEIGHT() {
        return GRID_HEIGHT;
    }

    public static int getBLOCK_SIZE() {
        return BLOCK_SIZE;
    }

    public boolean[][] getGrid() {
        return grid;
    }

    public void setGrid(boolean[][] grid) {
        this.grid = grid;
    }
    
}
