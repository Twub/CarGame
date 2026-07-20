package FileUtilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SoundPathsTest {

    @Test
    void soundPathsExposeResourcePaths() {
        assertEquals("/Sounds/bounce.wav", SoundPaths.BOUNCE_SOUND.getPathToSound());
        assertEquals("/Sounds/weapon_fire.wav", SoundPaths.WEAPON_FIRE_SOUND.getPathToSound());
    }
}
