package com.mycompany.tetriste2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Victor
 */
public class GrillePanel extends JPanel {

    private Grille grille;

    public GrillePanel(Grille grille) {
        this.grille = grille;
        setPreferredSize(new Dimension(grille.GRID_WIDTH * grille.BLOCK_SIZE,
                grille.GRID_HEIGHT * grille.BLOCK_SIZE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int y = 0; y < grille.GRID_HEIGHT; y++) {
            for (int x = 0; x < grille.GRID_WIDTH; x++) {
                if (grille.grid[y][x]) {
                    g.setColor(Color.BLUE);
                    g.fillRect(x * grille.BLOCK_SIZE, y * grille.BLOCK_SIZE,
                            grille.BLOCK_SIZE, grille.BLOCK_SIZE);
                }
                g.setColor(Color.BLACK);
                g.drawRect(x * grille.BLOCK_SIZE, y * grille.BLOCK_SIZE,
                        grille.BLOCK_SIZE, grille.BLOCK_SIZE);
            }
        }
    }
}
