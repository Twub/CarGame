package FileUtilities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public final class ImageUtility {

    public static Image loadImage(ImagePaths imagePaths){
        try {
            return ImageIO.read(
                    Objects.requireNonNull(
                            ImageUtility.class.getResource(imagePaths.getPathtoImage()),
                            "Resource not found: " + imagePaths.getPathtoImage()
                    )
            );
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Image resizeLoadImage(ImagePaths imagePaths, int width, int height){
        BufferedImage original = (BufferedImage) loadImage(imagePaths);

        BufferedImage resized = new BufferedImage(
                width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = resized.createGraphics();

        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BICUBIC);

        g.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g.drawImage(original, 0, 0, width, height, null);
        g.dispose();

        return resized;
    }
}
