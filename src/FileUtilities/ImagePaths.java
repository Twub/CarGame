package FileUtilities;

public enum ImagePaths {

    BACKGROUND("/Images/bilbana.png"),
    PAUSE_ICON("/Images/pause_icon.png"),
    PLAYER_IMAGE("/Images/bil.png"),
    OBSTACLE_PARTICLE("/Images/motorcycle_sprite.png"),
    WEAPON_PARTICLE("/Images/laser_particle.png");

    // Field (variable) to store the description text
    private String path;

    // Constructor (runs once for each constant above)
    private ImagePaths(String path) {
        this.path = path;
    }

    // Getter method to read the description
    public String getPathtoImage() {
        return path;
    }
}
