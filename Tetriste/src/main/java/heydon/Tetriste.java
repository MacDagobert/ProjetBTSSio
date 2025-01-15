/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package heydon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.text.html.HTML;
// Classe principale du jeu Tetris
public class Tetriste extends JFrame {

    private static final int GRID_WIDTH = 10;
    private static final int GRID_HEIGHT = 20;
    private static final int BLOCK_SIZE = 30;

    private boolean[][] grid = new boolean[GRID_HEIGHT][GRID_WIDTH]; // Grille de jeu
    private Tetromino currentTetromino; // Tetromino actuel
    private int tetrominoX = 4, tetrominoY = 0; // Position du tetromino

    private TetrisPanel tetrisPanel; // Panel personnalisé pour le rendu

    public Tetriste() {
        setTitle("Simple Tetris");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 600); // Définir une taille raisonnable
        setLocationRelativeTo(null);

        currentTetromino = new Tetromino("I"); // Commencer avec une barre

        tetrisPanel = new TetrisPanel();
        tetrisPanel.setPreferredSize(new Dimension(GRID_WIDTH * BLOCK_SIZE, GRID_HEIGHT * BLOCK_SIZE));

        add(tetrisPanel);

        pack();

        // Timer pour faire descendre automatiquement le tetromino
        Timer timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveTetrominoDown();
            }
        });
        timer.start();

        // Gestion des entrées clavier
        addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        moveTetrominoLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        moveTetrominoRight();
                        break;
                    case KeyEvent.VK_DOWN:
                        moveTetrominoDown();
                        break;
                    case KeyEvent.VK_UP:
                        rotateTetromino();
                        break;
                }
            }
        });

        setFocusable(true);
    }

    // Méthode pour faire pivoter le tetromino
    private void rotateTetromino() {
        currentTetromino.rotate();
        if (!canMove(tetrominoX, tetrominoY)) {
            // Si la rotation n'est pas possible, on revient à la position initiale
            currentTetromino.rotate();
            currentTetromino.rotate();
            currentTetromino.rotate();
        }
        tetrisPanel.repaint();
    }

    // Méthode pour déplacer le tetromino vers le bas
    public void moveTetrominoDown() {
        if (canMove(tetrominoX, tetrominoY + 1)) {
            tetrominoY++;
        } else {
            placeTetromino();
            resetTetromino();
        }
        tetrisPanel.repaint();
    }

    // Méthode pour déplacer le tetromino vers la gauche
    public void moveTetrominoLeft() {
        if (canMove(tetrominoX - 1, tetrominoY)) {
            tetrominoX--;
            tetrisPanel.repaint();
        }
    }

    // Méthode pour déplacer le tetromino vers la droite
    public void moveTetrominoRight() {
        if (canMove(tetrominoX + 1, tetrominoY)) {
            tetrominoX++;
            tetrisPanel.repaint();
        }
    }

    // Vérifie si le mouvement est possible
    public boolean canMove(int newX, int newY) {
        for (int[] block : currentTetromino.getCurrentShape()) {
            int x = newX + block[0];
            int y = newY + block[1];
            if (x < 0 || x >= GRID_WIDTH || y >= GRID_HEIGHT || (y >= 0 && grid[y][x])) {
                return false;
            }
        }
        return true;
    }

    // Place le tetromino sur la grille
    public void placeTetromino() {
        for (int[] block : currentTetromino.getCurrentShape()) {
            int x = tetrominoX + block[0];
            int y = tetrominoY + block[1];
            if (y >= 0) {
                grid[y][x] = true;
            }
        }
    }

   

    // Réinitialise le tetromino
    public void resetTetromino() {
        tetrominoX = 4;
        tetrominoY = 0;
        
        
        //currentTetromino = new Tetromino(Math.random() < 0.3 ? "I" : (Math.random() < 0.5 ? "O" : "L"));
        currentTetromino = Tetromino.getRandomTetromino();

    }

    // Classe interne pour le rendu du jeu
    private class TetrisPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            for (int y = 0; y < GRID_HEIGHT; y++) {
                for (int x = 0; x < GRID_WIDTH; x++) {
                    if (grid[y][x]) {
                        g.fillRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                    }
                    g.drawRect(x * BLOCK_SIZE, y * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
                }
            }

            g.setColor(Color.RED);

            for (int[] block : currentTetromino.getCurrentShape()) {
                int x = (tetrominoX + block[0]) * BLOCK_SIZE;
                int y = (tetrominoY + block[1]) * BLOCK_SIZE;
                g.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
    }

    // Point d'entrée du programme
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Tetriste().setVisible(true));
    }
}
