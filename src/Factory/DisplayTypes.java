package Factory;

public enum DisplayTypes {

    LABEL("DisplayLabel");

    // Field (variable) to store the description text
    private String displayType;

    // Constructor (runs once for each constant above)
    private DisplayTypes(String displayType) {
        this.displayType = displayType;
    }

    // Getter method to read the description
    public String getDisplayType() {
        return displayType;
    }
}
