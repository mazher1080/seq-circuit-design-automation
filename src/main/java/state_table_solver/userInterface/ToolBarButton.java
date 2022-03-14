package state_table_solver.userInterface;

import javax.swing.JButton;
import java.awt.*;

public class ToolBarButton extends JButton {
    public ToolBarButton(String label) {
        super(label);
        this.setFocusPainted(false);
    }
}
