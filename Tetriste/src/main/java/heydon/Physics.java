/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heydon;

/**
 *
 * @author greta
 */
/**
 * Classe représentant la physique du jeu Tetris.
 * Gère le mouvement et le placement des tetrominos dans la grille.
 */
public class Physics {
    private Grille grille; // Référence à l'objet Grille qui contient l'état de la grille
    private GrillePanel tetrisPanel; // Référence au panneau graphique pour afficher les changements
    private Tetromino currentTetromino; // Le tetromino actif (en mouvement)
    private int tetrominoX = 4; // Position initiale horizontale du tetromino (au centre de la grille)
    private int tetrominoY = -2; // Position initiale verticale du tetromino (au-dessus de la grille)

    /**
     * Constructeur de Physics.
     * Initialise la grille et génère un tetromino aléatoire.
     *
     * @param grille La grille de jeu
     */
    public Physics(Grille grille) {
        this.grille = grille;
        this.currentTetromino = Tetromino.getRandomTetromino(); // Génère un tetromino aléatoire
    }

    /**
     * Définit le panneau graphique associé à cette instance de Physics.
     *
     * @param panel Le panneau graphique à associer
     */
    public void setTetrisPanel(GrillePanel panel) {
        this.tetrisPanel = panel;
    }

    /**
     * Retourne le panneau graphique associé à cette instance de Physics.
     *
     * @return Le panneau graphique associé
     */
    public GrillePanel getTetrisPanel() {
        return tetrisPanel;
    }

    /**
     * Déplace le tetromino actif vers le bas.
     * Si le mouvement n'est pas possible, place le tetromino sur la grille et en génère un nouveau.
     */
    public void moveTetrominoDown() {
        if (canMove(tetrominoX, tetrominoY + 1)) { 
            tetrominoY++; // Incrémente la position Y (descend d'une ligne)
        } else {
            placeTetromino();
            resetTetromino(); // Génère un nouveau tetromino
        }
        tetrisPanel.repaint(); // Rafraîchit l'affichage graphique
    }

    /**
     * Déplace le tetromino actif vers la gauche si possible.
     */
    public void moveTetrominoLeft() {
        if (canMove(tetrominoX - 1, tetrominoY)) { 
            tetrominoX--; // Décrémente la position X (se déplace à gauche)
            tetrisPanel.repaint(); // Rafraîchit l'affichage graphique
        }
    }

    /**
     * Déplace le tetromino actif vers la droite si possible.
     */
    public void moveTetrominoRight() {
        if (canMove(tetrominoX + 1, tetrominoY)) { 
            tetrominoX++; // Incrémente la position X (se déplace à droite)
            tetrisPanel.repaint(); // Rafraîchit l'affichage graphique
        }
    }

    /**
     * Fait pivoter le tetromino actif dans le sens horaire si possible.
     */
    public void rotateTetromino() {
        currentTetromino.rotate(); // Effectue une rotation du tetromino

        if (!canMove(tetrominoX, tetrominoY)) { 
            // Si la rotation n'est pas possible, annule la rotation
            currentTetromino.rotate();
            currentTetromino.rotate();
            currentTetromino.rotate();
        }
        tetrisPanel.repaint(); // Rafraîchit l'affichage graphique après la rotation
    }

    /**
     * Vérifie si un mouvement est possible pour le tetromino actif à une nouvelle position.
     *
     * @param newX Nouvelle position X à vérifier
     * @param newY Nouvelle position Y à vérifier
     * @return true si le mouvement est possible, false sinon
     */
    public boolean canMove(int newX, int newY) {
        for (int[] block : currentTetromino.getCurrentShape()) { 
            int x = newX + block[0];
            int y = newY + block[1];

            if (x < 0 || x >= grille.getGridWidth() || y >= grille.getGridHeight() 
                || (y >= 0 && grille.isCellOccupied(y, x))) { 
                return false; 
            }
        }
        return true;
    }

    /**
     * Place le tetromino actif sur la grille (le rend immobile).
     */
    public void placeTetromino() {
        for (int[] block : currentTetromino.getCurrentShape()) { 
            int x = tetrominoX + block[0];
            int y = tetrominoY + block[1];

            if (y >= 0) { 
                grille.setCellOccupied(y, x, true);
            }
        }
    }

    /**
     * Réinitialise le jeu en générant un nouveau tetromino et en vérifiant si le jeu est terminé.
     */
    public boolean resetTetromino() {
    currentTetromino = Tetromino.getRandomTetromino(); 
    tetrominoX = grille.getGridWidth() / 2 - 2;
    tetrominoY = -2;

    if (!canMove(tetrominoX, tetrominoY)) { 
        return false; // Le jeu est terminé
    }
    return true; // Le jeu continue
}
    public boolean isGameOver() {
    return !canMove(tetrominoX, tetrominoY);
}

    /**
     * Retourne l'objet Tetromino actif (en mouvement).
     *
     * @return Le Tetromino actif
     */
    public Tetromino getCurrentTetromino() {
        return currentTetromino;
    }

    /**
     * Retourne la position X actuelle du Tetromino actif dans la grille.
     *
     * @return La position X du Tetromino actif
     */
    public int getTetrominoX() {
        return tetrominoX;
    }

    /**
     * Retourne la position Y actuelle du Tetromino actif dans la grille.
     *
     * @return La position Y du Tetromino actif
     */
    public int getTetrominoY() {
        return tetrominoY;
    }
}