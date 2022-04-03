package state_table_solver.userInterface;

import java.awt.Dimension;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import state_table_solver.userInterface.imageLoader.ImageLoader;

/**
 * ToolbarButton is a class used to wrap a JButton and
 * provide toolbar styling to the button.
 * 
 * @author Jacob Head
 */

public class ToolBarButton extends JButton {
    private static final Dimension DEFAULT_DIMENSION = new Dimension(141, 40);

    /**
     * Class constructor. Creates a ToolBarButton with specified label.
     * 
     * @param label The label of the toolbar button.
     */
    public ToolBarButton(String label, ImageLoader imgLoader) {
        super(label);
        this.setFocusPainted(false);
        this.setPreferredSize(DEFAULT_DIMENSION);
        this.setMaximumSize(DEFAULT_DIMENSION);
        this.setMinimumSize(DEFAULT_DIMENSION);

        Image iconImg = imgLoader.loadImage();
        if(iconImg != null) {
            ImageIcon icon = new ImageIcon(iconImg);
            this.setIcon(icon);
        }

    }

}
