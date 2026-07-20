package FileUtilities;

public enum SoundPaths {

    BOUNCE_SOUND("/Sounds/bounce.wav"),
    WEAPON_FIRE_SOUND("/Sounds/weapon_fire.wav");

    // Field (variable) to store the path text
    private String path;

    // Constructor (runs once for each constant above)
    private SoundPaths(String path) {
        this.path = path;
    }

    // Getter method to read the path
    public String getPathToSound() {
        return path;
    }
}
