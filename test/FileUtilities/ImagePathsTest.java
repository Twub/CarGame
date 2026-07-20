package FileUtilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ImagePathsTest {

    @Test
    void imagePathsExposeResourcePaths() {
        assertEquals("/Images/bilbana.png", ImagePaths.BACKGROUND.getPathtoImage());
        assertEquals("/Images/pause_icon.png", ImagePaths.PAUSE_ICON.getPathtoImage());
        assertEquals("/Images/bil.png", ImagePaths.PLAYER_IMAGE.getPathtoImage());
        assertEquals("/Images/motorcycle_sprite.png", ImagePaths.OBSTACLE_PARTICLE.getPathtoImage());
        assertEquals("/Images/laser_particle.png", ImagePaths.WEAPON_PARTICLE.getPathtoImage());
    }
}
