package GameObjects;

import FileUtilities.ImagePaths;
import FileUtilities.ImageUtility;

import java.awt.*;

public class Player {

    private static Player instance = null;
    private int yPosition;
    private int xPosition;
    private int speed = 10;
    private static  Image playerImage = ImageUtility.loadImage(ImagePaths.PLAYER_IMAGE);
    private Player(){
        System.out.println("I´m the player");
        resetPosition();
    }

    public static Player getInstance(){
        //if we haven't created the object yet...
        if (instance == null){
            instance = new Player();
        }
        return instance;
    }

    public int getY() {
        return yPosition;
    }

    public int getX() {
        return xPosition;
    }

    public void updatePosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void resetPosition() {
        yPosition = 800;
        xPosition = 667 / 2 - playerImage.getWidth(null) / 2;
    }

    public Image getPlayerImage(){
        return playerImage;
    }

    public int getSpeed(){
        return speed;
    }
}
