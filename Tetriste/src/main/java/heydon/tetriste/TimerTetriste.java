/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package heydon.tetriste;

import com.sun.java.accessibility.util.AWTEventMonitor;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 *
 * @author Heydon Simon
 */
public class TimerTetriste implements ActionListener {

    JFrame frame = new JFrame(); //Cadre 
    JLabel timeLabel = new JLabel(); // Temps actuel
    int elapsedTime = 1000; // Temps passé
    int seconds = 0;
    int minutes = 0;
    boolean started = false;
    String seconds_string = String.format("%02d", seconds);  // String va convertir , le %02d affiche deux zeros si rien , le seconds est le in parametre
    String minutes_string = String.format("%02d", minutes);

    Timer timer = new Timer(1000, new ActionListener() {
        // Ici c'est ce que va faire la minuterie toute les 1 secondes

        public void actionPerformed(ActionEvent e) {

            elapsedTime += 1000;
            minutes = (elapsedTime / 60000);
            seconds = (elapsedTime / 1000) % 60; // Ici le modulo sert à ce que les secondes n'aillent pas au dela de 60 
            seconds_string = String.format("%02d", seconds);
            minutes_string = String.format("%02d", minutes);
            timeLabel.setText(minutes_string + ":" + seconds_string);

        }
    });

    TimerTetriste() {

        timeLabel.setText(minutes_string + ":" + minutes_string); //Contenir le timer
        timeLabel.setBounds(100, 100, 200, 100); // taille du timer
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 35)); //Police timer
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));  // Bordure 
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(JTextField.CENTER);

        frame.add(timeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420); // Taille de la fenêtre
        frame.setLayout(null);// Layout du timer ici nulle
        frame.setVisible(true); // Afficher fenetre doit toujours être a la fin pour être sur qu'elle affiche parametres avant

        // Le key listerner qui démarre le chrono et qui l'arrête + remet a 0
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!started && (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_DOWN)) {
                    started = true;
                    timer.start();
                } else if (started && e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    started = false;
                    timer.stop();
                    elapsedTime=0;
                }
            }
        });
        frame.setFocusable(true);
        frame.requestFocusInWindow();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
