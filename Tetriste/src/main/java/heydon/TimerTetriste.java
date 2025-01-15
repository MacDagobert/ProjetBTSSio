package heydon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//* simon heydon
//* Ici on a le constructeur du timer et les fonctions qui manipule ce timer

public class TimerTetriste extends JPanel {
    private JLabel timeLabel; //Pour Afficher le temps
    private Timer timer;
    private int elapsedTime = 0;

    public TimerTetriste() {  // Constructeur du timer
        setLayout(new BorderLayout()); // Position de fenetre
        setPreferredSize(new Dimension(200, 100)); // Taille de la fenêtre

        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER); // Le timer 
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);

        add(timeLabel, BorderLayout.CENTER); // Ajoute le label au Panel

        timer = new Timer(1000, new ActionListener() {  // Ici toutes les 1 seconde = 1000 , Ce que va faire le timer ici il s'incremente
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                updateDisplay();
            }
        });
    }

    public void starttimer() { // Fonction start a appelé
        timer.start();
    }

    public void stoptimer() {  // Fonction stop a appelé
        timer.stop();
    }

    public void resettimer() { //Fonction reset a appelé
        timer.stop();
        elapsedTime = 0;
        updateDisplay();
    }

    private void updateDisplay() { // Pour l'affichage en hh:mm:ss
        int hours = elapsedTime / 3600;
        int minutes = (elapsedTime % 3600) / 60;
        int seconds = elapsedTime % 60;
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText(time);
    }
}