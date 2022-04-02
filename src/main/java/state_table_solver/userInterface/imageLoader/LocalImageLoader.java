package state_table_solver.userInterface.imageLoader;

import java.awt.Image;

import javax.imageio.ImageIO;

public class LocalImageLoader implements ImageLoader {
    private String localPath;

    public LocalImageLoader(String localPath) {
        this.localPath = localPath;
    }

    @Override
    public Image loadImage() {
        try {
            Image img = ImageIO.read(getClass().getResource(localPath));
            return img;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }
    
}
