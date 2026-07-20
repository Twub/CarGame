package FileUtilities;

import javax.sound.sampled.*;
import java.io.IOException;
import java.util.Objects;

public final class SoundUtility {

    public static void play(SoundPaths soundPath){
        try {
            AudioInputStream audio = AudioSystem.getAudioInputStream(
                    Objects.requireNonNull(SoundUtility.class.getResourceAsStream(soundPath.getPathToSound()))
            );
            Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
        }catch (UnsupportedAudioFileException | IOException | LineUnavailableException e){
            e.printStackTrace();
        }
    }
}
