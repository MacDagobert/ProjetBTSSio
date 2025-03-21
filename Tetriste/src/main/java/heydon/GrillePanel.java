/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heydon;

/**
 *
 * @author greta
 */
import javax.swing.*;
import java.awt.*;


/**
 * Classe GrillePanel représentant l'interface graphique de la grille du jeu Tetris.
 * Cette classe est responsable de l'affichage de la grille, des blocs occupés, du tetromino actif et du score.
 */
public class GrillePanel extends JPanel {
    private Grille grille; // Référence à l'objet Grille qui contient l'état de la grille
    private Physics physics; // Référence à l'objet Physics qui gère la logique du jeu
    private Score score; // Référence à l'objet Score pour afficher le score
    private int lignesEffectuees; // Nombre total de lignes complétées (pour calculer le niveau)

    /**
     * Constructeur de la classe GrillePanel.
     * Initialise la grille, la logique du jeu et le score, et configure les dimensions et l'apparence du panneau.
     *
     * @param grille  L'objet Grille représentant l'état de la grille
     * @param physics L'objet Physics qui gère la logique du jeu
     * @param score   L'objet Score pour gérer et afficher le score
     */
    public GrillePanel(Grille grille, Physics physics, Score score) {
        this.grille = grille; // Initialise la grille
        this.physics = physics; // Initialise la logique du jeu
        this.score = score; // Initialise le score du jeu
        this.lignesEffectuees = 0; // Initialise le compteur de lignes complétées

        // Définit les dimensions préférées du panneau en fonction de la taille de la grille
        setPreferredSize(new Dimension(
                grille.getGridWidth() * grille.getBlockSize(), // Largeur totale (colonnes * taille d'un bloc)
                grille.getGridHeight() * grille.getBlockSize() + 80 // Hauteur totale (lignes * taille d'un bloc + espace pour le score)
        ));

        // Définit la couleur de fond du panneau à noir
        setBackground(Color.BLACK);
    }

    /**
     * Met à jour le nombre total de lignes complétées.
     *
     * @param lignes Le nombre de nouvelles lignes complétées
     */
    public void ajouterLignesEffectuees(int lignes) {
    this.lignesEffectuees += lignes;
    System.out.println("Lignes effectuées : " + lignesEffectuees); // Message de débogage
    repaint(); // Redessine le panneau pour mettre à jour l'affichage du niveau
}

    /**
     * Calcule le niveau actuel en fonction des lignes complétées.
     *
     * @return Le niveau actuel (1 pour 0-49 lignes, 2 pour 50-99 lignes, etc.)
     */
   public int getNiveau() {
    int niveau = (lignesEffectuees / 20) + 1;
    System.out.println("Niveau actuel : " + niveau); // Message de débogage
    return niveau;
}

    /**
     * Méthode appelée automatiquement pour dessiner le composant graphique.
     * Elle dessine les lignes de la grille, les blocs occupés, le tetromino actif et le score.
     *
     * @param g L'objet Graphics utilisé pour dessiner les éléments graphiques
     */
  @Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g); // Appelle la méthode par défaut pour nettoyer l'écran

    int blockSize = grille.getBlockSize(); // Taille d'un bloc en pixels

    // Dessiner les lignes de la grille pour un effet visuel
    g.setColor(Color.DARK_GRAY); // Couleur des lignes de la grille
    for (int row = 0; row <= grille.getGridHeight(); row++) { // Dessine les lignes horizontales
        g.drawLine(0, row * blockSize, grille.getGridWidth() * blockSize, row * blockSize);
    }
    for (int col = 0; col <= grille.getGridWidth(); col++) { // Dessine les lignes verticales
        g.drawLine(col * blockSize, 0, col * blockSize, grille.getGridHeight() * blockSize);
    }

    // Dessiner les blocs occupés dans la grille
    for (int row = 0; row < grille.getGridHeight(); row++) { // Parcourt chaque ligne
        for (int col = 0; col < grille.getGridWidth(); col++) { // Parcourt chaque colonne
            if (grille.isCellOccupied(row, col)) { // Vérifie si une cellule est occupée
                drawBlock(g, col * blockSize, row * blockSize, Color.CYAN); // Dessine un bloc bleu clair
            }
        }
    }

    // Dessiner le tetromino actif (la pièce en mouvement)
    if (physics != null) { 
        Tetromino currentTetromino = physics.getCurrentTetromino(); 
        int tetrominoX = physics.getTetrominoX(); 
        int tetrominoY = physics.getTetrominoY(); 

        if (currentTetromino != null) { 
            for (int[] block : currentTetromino.getCurrentShape()) { 
                int x = (tetrominoX + block[0]) * blockSize;
                int y = (tetrominoY + block[1]) * blockSize;
                drawBlock(g, x, y, Color.YELLOW); // Dessine le tetromino actif en jaune
            }
        }
    }

    // Dessiner le score et le niveau en bas du panneau
    g.setColor(Color.WHITE); // Couleur du texte
    g.setFont(new Font("Arial", Font.BOLD, 20)); // Police et taille du texte

    int infoYPosition = grille.getGridHeight() * blockSize + 20; // Position Y en dessous de la grille

    g.drawString("Score: " + score.getScore(), 10, infoYPosition); // Affiche le score en bas à gauche du panneau
    g.drawString("Niveau: " + getNiveau(), getWidth() - 150, infoYPosition); // Affiche le niveau en bas à droite du panneau
}

    /**
     * Méthode utilitaire pour dessiner un bloc à une position donnée avec une couleur donnée.
     *
     * @param g     L'objet Graphics utilisé pour dessiner les éléments graphiques
     * @param x     La position X en pixels où dessiner le bloc
     * @param y     La position Y en pixels où dessiner le bloc
     * @param color La couleur utilisée pour dessiner le bloc
     */
    private void drawBlock(Graphics g, int x, int y, Color color) {
        int blockSize = grille.getBlockSize(); // Taille d'un bloc en pixels

        g.setColor(color); 
        g.fillRect(x, y, blockSize, blockSize); // Remplit un rectangle avec la couleur spécifiée

        g.setColor(color.brighter()); 
        g.drawLine(x, y, x + blockSize - 1, y); // Dessine une ligne claire sur le bord supérieur du bloc
        g.drawLine(x, y, x, y + blockSize - 1); // Dessine une ligne claire sur le bord gauche du bloc

        g.setColor(color.darker());
        g.drawLine(x + blockSize - 1, y, x + blockSize - 1, y + blockSize - 1); // Dessine une ligne sombre sur le bord droit du bloc
        g.drawLine(x, y + blockSize - 1, x + blockSize - 1, y + blockSize - 1); // Dessine une ligne sombre sur le bord inférieur du bloc
    }
}