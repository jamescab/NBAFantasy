package ui.collect;

import javax.swing.*;
import java.awt.*;

public class AthleteCollector {
    private JPanel athletepanel;

    public AthleteCollector() {
        athletepanel = new JPanel(new GridLayout(0, 2));
    }

    //EFFECTS: Collects desired athlete's name through GUI input and is returned as a String
    public String getAthleteName() {
        JTextField text1 = new JTextField();

        athletepanel.add(new JLabel("Athlete Name: "));
        athletepanel.add(text1);

        int result = JOptionPane.showConfirmDialog(null, athletepanel, "Enter Athlete", JOptionPane.OK_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return text1.getText();
        }
        return null;
    }

}
