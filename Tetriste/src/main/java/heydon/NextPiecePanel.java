package heydon;

import javax.swing.*;
import java.awt.*;

/**
 * Classe NextPiecePanel pour afficher la prochaine pièce à venir.
 */
public class NextPiecePanel extends JPanel {
    private Physics physics;

    public NextPiecePanel(Physics physics) {
        this.physics = physics;
        setPreferredSize(new Dimension(120, 120)); // Taille de l'encart
        setBackground(Color.BLACK); // Fond noir
    }

   
}