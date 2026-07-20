package FileUtilities;

import org.junit.jupiter.api.Test;

import java.awt.Image;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ImageUtilityTest {

    @Test
    void loadImageLoadsResourceImage() {
        Image image = ImageUtility.loadImage(ImagePaths.PLAYER_IMAGE);

        assertNotNull(image);
        assertEquals(60, image.getWidth(null));
        assertEquals(100, image.getHeight(null));
    }

    @Test
    void resizeLoadImageReturnsRequestedSize() {
        Image image = ImageUtility.resizeLoadImage(ImagePaths.OBSTACLE_PARTICLE, 40, 60);

        assertNotNull(image);
        assertEquals(40, image.getWidth(null));
        assertEquals(60, image.getHeight(null));
    }
}
