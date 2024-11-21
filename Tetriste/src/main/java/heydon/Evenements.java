/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heydon;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Heydon Simon
 * @class Ecoute des Evenement clavier
 * @fonction Timer
 *
 */
public class Evenements {

//    public void touchePresse(KeyEvent e) {
//        int keyCode = e.getKeyCode();
//
//        // Vérifie si la touche pressée est une touche directionnelle
//        if (keyCode == KeyEvent.VK_UP || keyCode == KeyEvent.VK_DOWN
//                || keyCode == KeyEvent.VK_LEFT || keyCode == KeyEvent.VK_RIGHT) {
//            System.out.println(true);  // Affiche true pour une touche directionnelle
//        } else {
//            System.out.println(false); // Affiche false pour une autre touche
//        }
//    }
    /**
     * fonction fleche gauche : flecheGauche Ecoute le keyEvent
     */
    public void flecheGauche(KeyEvent e) {
        int keyCode = e.getExtendedKeyCode();

        if (keyCode == KeyEvent.VK_LEFT) {
            System.out.println(true);
        }

    }

    /**
     * fonction fleche droitee : flecheDauche Ecoute le keyEvent
     */
    public void flecheDroite(KeyEvent e) {
        int keyCode = e.getExtendedKeyCode();

        if (keyCode == KeyEvent.VK_RIGHT) {
            System.out.println(true);
        }

    }

    /**
     * fonction fleche bas : flecheBas Ecoute le keyEvent
     */
    public void flecheBas(KeyEvent e) {
        int keyCode = e.getExtendedKeyCode();

        if (keyCode == KeyEvent.VK_DOWN) {
            System.out.println(true);
        }

    }

    public static void main(String[] args) {
        JFrame cadretimer = new JFrame();
        TimerTetriste timertetriste = new TimerTetriste();
        cadretimer.add(timertetriste);

        cadretimer.pack();
        cadretimer.setVisible(true);
        timertetriste.starttimer();
        Evenements evenements = new Evenements();

        JFrame frame = new JFrame("Test des événements clavier");

        frame.addKeyListener(new KeyAdapter() {        //Permet composant graphique de reagir evenement de pression 
            //KeyAddapter classe abstraite implemente keylistener et pas les autres methodes
            public void keyPressed(KeyEvent e) {    //keyPressed seule méthode appelé 
//                evenements.touchePresse(e);     // Tester la méthode générale pour les touches directionnelles
                evenements.flecheGauche(e);     // Tester la flèche gauche
                evenements.flecheDroite(e);     // Tester la flèche droite
                evenements.flecheBas(e);        // Tester la flèche bas
            }
        });
        frame.setSize(400, 300);  // Taille de la fenêtre
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fermer la fenêtre quand on clique 
        frame.setVisible(true);   // Afficher la fenêtre

        // Demander à la fenêtre de recevoir les événements clavier 
        frame.requestFocusInWindow();
    }
}

