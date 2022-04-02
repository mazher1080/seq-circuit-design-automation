package state_table_solver.userInterface.imageLoader;

import java.awt.Image;

/**
 * <p> A public interface to load images using various strategies.
 * 
 * @author Jacob Head
 */

public interface ImageLoader {
    /**
     * Returns a loaded image. If image loading fails
     * should return null.
     * 
     * @return The loaded image
     */
    public Image loadImage();
}
