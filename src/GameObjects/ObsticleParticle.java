package GameObjects;

import FileUtilities.ImagePaths;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class ObsticleParticle extends Particles{

    private static final int MIN_X_POSITION = 63;
    private static final int MAX_X_POSITION = 543;

    public ObsticleParticle(){
        /*
        DEFAULT VALUES
         */
        setWidth(40);
        setHeight(60);
        setX(generateXposition());
        setY(-60);
        setDiameter(28);
        setSpeed(2);
        setRadius((double) getDiameter() / 2);
        setParticleColor(Color.RED);

        setParticleImage(ImagePaths.OBSTACLE_PARTICLE, getWidth(), getHeight());
    }

    private int generateXposition(){
        return ThreadLocalRandom.current().nextInt(MIN_X_POSITION, MAX_X_POSITION + 1);
    }

    @Override
    public void updatePosition() {
        //super.updatePosition();
        setY(getY() + getSpeed());
    }
}
