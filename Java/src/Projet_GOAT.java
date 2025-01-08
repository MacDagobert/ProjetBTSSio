package src;


import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 *
 * @author Victor
 */

 
public class Projet_GOAT {

    public class Fenetre extends JFrame{
        private JPanel pan = new JPanel();// on créer la fenêtre
        private JButton bouton = new JButton("Jouer");//on créer le boutton sur le principe d'un input
        private JButton bouton2 = new JButton("se connecter ou s'enregistrer");
    
    public Fenetre () {
        this.setTitle("Bouton jouer");
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //Ajout du bouton à notre content pane
        pan.add(bouton);// ajouter l'existence d'un bouton
        this.setContentPane(pan);// Ajouter le bouton comme conteneur centrale de la fenêtre
        this.setVisible(true);// Rend visible la fenêtre
        pan.add(bouton2);
        this.setContentPane(pan);
        this.setVisible(true);
        }      

        // Background de fenêtre
        

    }
    public static void main(String[] args) {
    // Création d'une instance de la fenêtre pour afficher l'interface
    Fenetre fenetre = new Projet_GOAT().new Fenetre();}

}

