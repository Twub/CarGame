package GameObjects;

import org.junit.jupiter.api.Test;

import java.awt.Color;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObsticleParticleTest {

    @Test
    void constructorSetsExpectedDefaults() {
        ObsticleParticle obstacle = new ObsticleParticle();

        assertTrue(obstacle.getX() >= 63 && obstacle.getX() <= 543);
        assertEquals(-60, obstacle.getY());
        assertEquals(40, obstacle.getWidth());
        assertEquals(60, obstacle.getHeight());
        assertEquals(28, obstacle.getDiameter());
        assertEquals(2, obstacle.getSpeed());
        assertEquals(14.0, obstacle.getRadius());
        assertEquals(Color.RED, obstacle.getParticleColor());
        assertNotNull(obstacle.getParticleImage());
    }

    @Test
    void generatedXPositionAlwaysStaysInsideRoadBounds() {
        for (int i = 0; i < 1_000; i++) {
            ObsticleParticle obstacle = new ObsticleParticle();

            assertTrue(
                    obstacle.getX() >= 63 && obstacle.getX() <= 543,
                    "Expected x inside 63..543, got " + obstacle.getX()
            );
        }
    }

    @Test
    void updatePositionMovesObstacleDown() {
        ObsticleParticle obstacle = new ObsticleParticle();
        int startY = obstacle.getY();

        obstacle.updatePosition();

        assertEquals(startY + obstacle.getSpeed(), obstacle.getY());
    }
}
