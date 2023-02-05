package ui.collect;

import javax.swing.*;
import java.awt.*;

public class TeamCollector {
    private JPanel teamPanel;

    public TeamCollector() {
        teamPanel = new JPanel(new GridLayout(0, 2));
    }

    //EFFECTS: Collects info for a team's name and colour through GUI and returns it
    public Info getTeamInfo() {
        JTextField text1 = new JTextField();
        JTextField text2 = new JTextField();

        teamPanel.add(new JLabel("Team Name: "));
        teamPanel.add(text1);
        teamPanel.add(new JLabel("Team Colour: "));
        teamPanel.add(text2);

        int result = JOptionPane.showConfirmDialog(null, teamPanel, "Enter Player", JOptionPane.OK_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            JOptionPane.showMessageDialog(null, "Player added to fantasy season!");
            return new Info(text1.getText(), text2.getText());
        }
        return null;
    }


}
