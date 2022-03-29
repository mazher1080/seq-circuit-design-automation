package state_table_solver.userInterface;

import javax.swing.JToolBar;

/**
 * Toolbar is a component used to display various tool bar buttons.
 * 
 * @author Jacob Head
 * 
 */

public class ToolBar extends JToolBar {
    private ToolBarButton[] buttons;

    /**
     * Class constructor. Creates tool bar and adds buttons to it.
     */
    public ToolBar() {
        super("ToolBar", JToolBar.HORIZONTAL);
        this.setFloatable(false);

        this.buttons = new ToolBarButton[7];
        buttons[0] = new ToolBarButton("New");
        buttons[1] = new ToolBarButton("Open");
        buttons[2] = new ToolBarButton("Save");
        buttons[3] = new ToolBarButton("Save As");
        buttons[4] = new ToolBarButton("Derive");
        buttons[5] = new ToolBarButton("Export");
        buttons[6] = new ToolBarButton("Add State");

        addButtons();
    }

    /**
     * Helper function for adding toolbar buttons to tool bar.
     */
    private void addButtons() {
        for(ToolBarButton btn : this.buttons) {
            this.add(btn);
        }
    }

    /**
     * Returns the toolbar button at specified index.
     * 
     * @param i The index of the button on the toolbar to get.
     * @return Toolbar button at specified index.
     */
    public ToolBarButton getButton(int i) {
        return buttons[i];
    }
}
