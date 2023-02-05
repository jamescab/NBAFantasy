package ui.collect;

import javax.swing.*;
import java.awt.*;

public class PromptCollector {
    private JPanel promptPanel;

    public PromptCollector() {
        promptPanel = new JPanel(new GridLayout(0, 2));
    }

    //EFFECTS: Shows a prompt on whether the user would like to add more active players and returns that value
    public String getAnswer() {
        promptPanel.add(new JLabel("Add more players? (Type Y or N) "));

        int result = JOptionPane.showConfirmDialog(null, promptPanel, "More Players?", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            return "Y";
        }
        return "N";
    }
}
