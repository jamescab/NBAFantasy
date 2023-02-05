package ui.collect;

import model.Athlete;

import javax.swing.*;
import java.awt.*;

public class PointCollector {
    private JPanel pointPanel;

    public PointCollector() {
        pointPanel = new JPanel(new GridLayout(0, 2));
    }

    public int getPoints(Athlete athlete, int weekNum) {
        JTextField text1 = new JTextField();

        pointPanel.add(new JLabel("Points earned by " + athlete.getAthleteName() + " in week  " + weekNum));
        pointPanel.add(text1);

        int result = JOptionPane.showConfirmDialog(null, pointPanel, "Points earned", JOptionPane.OK_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return Integer.parseInt(text1.getText());
        }
        return 0;
    }
}
