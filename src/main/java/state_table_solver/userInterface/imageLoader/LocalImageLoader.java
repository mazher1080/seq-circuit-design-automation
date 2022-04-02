package state_table_solver.userInterface.imageLoader;

import java.awt.Image;

import javax.imageio.ImageIO;

/**
 * <p> LocalImageLoader is a ImageLoader strategy to load images from
 * the projects local resources.
 * 
 * @author Jacob Head
 */

public class LocalImageLoader implements ImageLoader {
    private String localPath;

    /**
     * Class constructor.
     * 
     * @param localPath File path of the local image.
     */
    public LocalImageLoader(String localPath) {
        this.localPath = localPath;
    }

    /**
     * Strategy for loading the image. Returns null if image does
     * not load properly.
     * 
     * @return the loaded image.
     */
    @Override
    public Image loadImage() {
        try {
            Image img = ImageIO.read(getClass().getResource(localPath));
            return img;
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }
    
}
