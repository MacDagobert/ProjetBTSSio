package heydon;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimerTetriste extends JPanel {
    private JLabel timeLabel;
    private Timer timer;
    private int elapsedTime = 0;

    public TimerTetriste() {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(200, 100));

        timeLabel = new JLabel("00:00:00", SwingConstants.CENTER);
        timeLabel.setFont(new Font("Verdana", Font.PLAIN, 30));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setOpaque(true);

        add(timeLabel, BorderLayout.CENTER);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                elapsedTime++;
                updateDisplay();
            }
        });
    }

    public void starttimer() {
        timer.start();
    }

    public void stoptimer() {
        timer.stop();
    }

    public void resettimer() {
        timer.stop();
        elapsedTime = 0;
        updateDisplay();
    }

    private void updateDisplay() {
        int hours = elapsedTime / 3600;
        int minutes = (elapsedTime % 3600) / 60;
        int seconds = elapsedTime % 60;
        String time = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText(time);
    }
}