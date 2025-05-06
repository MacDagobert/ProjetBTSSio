/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tetriste2;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Victor
 */
public class Tetriste2 {
    
    public static void main(String[] args){
        JFrame Tetriste2 = new JFrame();
         Grille grille = new Grille(); // Création de l'instance
        Tetriste2.setDefaultCloseOperation(Tetriste2.EXIT_ON_CLOSE);
        
        
        JPanel panelJeu = new JPanel();
        panelJeu.setBackground(Color.RED);
        //Taille du panel
        panelJeu.setPreferredSize(new Dimension(1000, 800));
        // Ajoute au panel 
       Tetriste2.add(panelJeu);
       // Regle la taille du frame au panel
       Tetriste2.pack();
       //redn visible
       Tetriste2.setVisible(true);
       
       panelJeu.add(new GrillePanel(grille));
       
    }
    
}
