package state_table_solver.userInterface;

import java.awt.Dimension;

import javax.swing.JToolBar;

import state_table_solver.userInterface.imageLoader.LocalImageLoader;

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
        buttons[0] = new ToolBarButton("New", new LocalImageLoader("/images/file-plus-outline.png"));
        buttons[1] = new ToolBarButton("Open", new LocalImageLoader("/images/folder-open-outline.png"));
        buttons[2] = new ToolBarButton("Save", new LocalImageLoader("/images/content-save-outline.png"));
        buttons[3] = new ToolBarButton("Save As", new LocalImageLoader("/images/content-save-edit-outline.png"));
        buttons[4] = new ToolBarButton("Derive", new LocalImageLoader("/images/function-variant.png"));
        buttons[5] = new ToolBarButton("Export VHDL", new LocalImageLoader("/images/export-variant.png"));
        buttons[6] = new ToolBarButton("Add State", new LocalImageLoader("/images/plus.png"));

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
