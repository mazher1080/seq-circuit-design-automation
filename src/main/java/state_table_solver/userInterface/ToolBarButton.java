package state_table_solver.userInterface;

import javax.swing.JButton;

/**
 * ToolbarButton is a class used to wrap a JButton and
 * provide toolbar styling to the button.
 * 
 * @author Jacob Head
 */

public class ToolBarButton extends JButton {

    /**
     * Class constructor. Creates a ToolBarButton with specified label.
     * 
     * @param label The label of the toolbar button.
     */
    public ToolBarButton(String label) {
        super(label);
        this.setFocusPainted(false);
    }
}
