/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package heydon;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSetMetaData;
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
    private TetrisPanel compteurPanel;
    public int score ;
    private JLabel resultat;
    
    
    public Tetriste() {
        setTitle("Tetriste");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Définir une taille raisonnable
        setLocationRelativeTo(null);
        
        JPanel main = new JPanel(new BorderLayout());
        FonctionImage fondPanel = new FonctionImage("C:/Users/Victor/Desktop/ProjetBTSSio/site/ttrist.jpg");
        fondPanel.setLayout(new BorderLayout());

        currentTetromino = new Tetromino("I"); // Commencer avec une barre

        tetrisPanel = new TetrisPanel();
        tetrisPanel.setPreferredSize(new Dimension(GRID_WIDTH * BLOCK_SIZE, GRID_HEIGHT * BLOCK_SIZE));
        tetrisPanel.setOpaque(false);
        tetrisPanel.setVisible(true);
        
        
        main.add(tetrisPanel, BorderLayout.CENTER);
       
        
        
        JPanel compteurpanel = new JPanel();
        fondPanel.setPreferredSize(new Dimension(100, 200));
        JLabel infoLabel = new JLabel("Informations du jeu" );
        resultat = new JLabel("Votre score : " + this.score );
        fondPanel.setVisible(true);
        fondPanel.add(infoLabel);
        
        fondPanel.add(resultat);
        main.add(fondPanel , BorderLayout.NORTH);
        
        
        add(main);
        main.setVisible(true);
        
        setVisible(true);
        
        
        pack();

        // Timer pour faire descendre automatiquement le tetromino
        Timer timer = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                moveTetrominoDown();
                winpoint(1);
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

    public void moveDown() { 
        // Parcourir chaque colonne de gauche à droite
        for (int x = 0; x < GRID_WIDTH; x++) {
            // Parcourir chaque ligne du bas vers le haut (pour éviter de déplacer un bloc plusieurs fois)
            for (int y = GRID_HEIGHT - 2; y >= 0; y--) {
                // Vérifier s'il y a un bloc à la position actuelle et si la case en dessous est vide
                if (grid[y][x] && !grid[y + 1][x]) {
                    // Déplacer le bloc vers le bas
                    grid[y + 1][x] = true; // Placer le bloc dans la case du dessous
                    grid[y][x] = false;    // Vider la case actuelle

                    // Afficher un message indiquant le déplacement
                    System.out.println("Bloc déplacé vers le bas à la position (" + y + ", " + x + ")");
                }
            }
        }
    }

    public void addScoreScreen( int addScoreValue){
        score = score + addScoreValue;
        System.err.println("toto");
        resultat.setText("Votre score est égal à "+score);
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

    public void winpoint(int addScoreValue) {
        // Parcourir toutes les lignes de la grille, de bas en haut
        for (int y = GRID_HEIGHT - 1; y >= 0; y--) {
            // Supposer initialement que la ligne est remplie
            boolean ligneRemplie = true;

            // Vérifier chaque cellule de la ligne actuelle
            for (int x = 0; x < GRID_WIDTH; x++) {
                // Si une cellule est vide (false), la ligne n'est pas remplie
                if (!grid[y][x]) {
                    ligneRemplie = false;
                    // Sortir de la boucle interne, car on sait déjà que la ligne n'est pas remplie
                    break;
                }
            }
            if (ligneRemplie) {
                System.out.println("Ligne remplie!");
                //System.exit(0);
                for (int x = 0; x < GRID_WIDTH; x++) {
                    grid[y][x] = false;
                }
                moveDown();
                addScoreScreen(addScoreValue);
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

            g.setColor(Color.BLUE);

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
