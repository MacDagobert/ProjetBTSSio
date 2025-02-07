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
public class FonctionImage extends JPanel {
    private Image backgroundImage;

    // Constructeur pour charger l'image
    public FonctionImage(String imagePath) {
        // Charger l'image depuis le chemin spécifié
        backgroundImage = new ImageIcon(imagePath).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Appeler la méthode de base pour dessiner les composants
        // Dessiner l'image en arrière-plan (ajustée à la taille du JPanel)
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}

