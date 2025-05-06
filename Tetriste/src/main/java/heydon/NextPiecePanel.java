/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heydon;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author Victor
 */
//public class NextPiecePanel {
//    private NextPiece nextpiece;
//     
//    public NextPiecePanel (NextPiece nextpiece){
//        this.nextpiece  = nextpiece;
//    
//    }
//    
//    @Override
//    protected void paintComponent(Graphics g) {
//        super.paintComponent(g); // Appelle la méthode par défaut pour nettoyer l'écran
//
//        int blockSize = nextpiece.getBlockSize(); // Taille d'un bloc en pixels
//
//        // Dessiner les lignes de la nextpiece pour un effet visuel
//        g.setColor(Color.DARK_GRAY); // Couleur des lignes de la nextpiece
//        for (int row = 0; row <= nextpiece.getGridHeight(); row++) { // Dessine les lignes horizontales
//            g.drawLine(0, row * blockSize, nextpiece.getGridWidth() * blockSize, row * blockSize);
//        }
//        for (int col = 0; col <= nextpiece.getGridWidth(); col++) { // Dessine les lignes verticales
//            g.drawLine(col * blockSize, 0, col * blockSize, nextpiece.getGridHeight() * blockSize);
//        }
//
//        // Dessiner les blocs occupés dans la nextpiece
//        for (int row = 0; row < nextpiece.getGridHeight(); row++) { // Parcourt chaque ligne
//            for (int col = 0; col < nextpiece.getGridWidth(); col++) { // Parcourt chaque colonne
//                if (nextpiece.isCellOccupied(row, col)) { // Vérifie si une cellule est occupée
//                    drawBlock(g, col * blockSize, row * blockSize, Color.CYAN); // Dessine un bloc bleu clair
//                }
//            }
//        }
//    }
//}
