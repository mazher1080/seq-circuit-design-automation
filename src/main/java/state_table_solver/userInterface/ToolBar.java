package state_table_solver.userInterface;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import java.awt.*;

// TODO implement actual buttons
import javax.swing.JButton;

public class ToolBar extends JToolBar {
    private ToolBarButton[] buttons;

    public ToolBar() {
        super("ToolBar", JToolBar.HORIZONTAL);
        this.setFloatable(false);

        this.buttons = new ToolBarButton[5];
        buttons[0] = new ToolBarButton("New");
        buttons[1] = new ToolBarButton("Open");
        buttons[2] = new ToolBarButton("Save");
        buttons[3] = new ToolBarButton("Save As");
        buttons[4] = new ToolBarButton("Derive");

        addButtons();
    }

    private void addButtons() {
        for(ToolBarButton btn : this.buttons) {
            this.add(btn);
        }
    }

    public ToolBarButton getButton(int i) {
        return buttons[i];
    }
}
