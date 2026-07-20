package GameObjects;

import FileUtilities.ImagePaths;

import java.awt.*;

public class WeaponParticle extends Particles{

    public WeaponParticle(int xPosition, int yPosition){
        /*
        DEFAULT VALUES
         */
        setX(xPosition);
        setY(yPosition);
        setDiameter(10);
        setSpeed(7);
        setRadius((double) getDiameter() / 2);
        setParticleColor(generateRandomColor());
        setParticleImage(ImagePaths.WEAPON_PARTICLE);
    }



}
