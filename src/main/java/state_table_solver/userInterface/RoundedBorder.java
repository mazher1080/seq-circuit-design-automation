package state_table_solver.userInterface;

import javax.swing.border.Border;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;

public class RoundedBorder implements Border {
    private int radius;

    public RoundedBorder(int radius) {
        this.radius = radius;
    }


    /** 
     * @param c
     * @return Insets
     */
    public Insets getBorderInsets(Component c) {
        return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
    }


    
    /** 
     * @return boolean
     */
    public boolean isBorderOpaque() {
        return false;
    }

    
    /** 
     * @param c
     * @param g
     * @param x
     * @param y
     * @param width
     * @param height
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, radius, radius);
    }

}
