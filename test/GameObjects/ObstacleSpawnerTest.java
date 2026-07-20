package GameObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObstacleSpawnerTest {

    @Test
    void constructorCreatesCountdownInsideExpectedRange() {
        ObstacleSpawner spawner = new ObstacleSpawner();

        assertTrue(spawner.getSpawnCountdown() >= 1000 && spawner.getSpawnCountdown() <= 5000);
    }

    @Test
    void setRandomSpawnTimeCreatesCountdownInsideExpectedRange() {
        ObstacleSpawner spawner = new ObstacleSpawner();

        spawner.setRandomSpawnTime();

        assertTrue(spawner.getSpawnCountdown() >= 1000 && spawner.getSpawnCountdown() <= 5000);
    }

    @Test
    void updateSpawnCountdownCanAddAndSubtract() {
        ObstacleSpawner spawner = new ObstacleSpawner();
        int start = spawner.getSpawnCountdown();

        spawner.updateSpawnCountdown(30, "-");
        assertEquals(start - 30, spawner.getSpawnCountdown());

        spawner.updateSpawnCountdown(10, "+");
        assertEquals(start - 20, spawner.getSpawnCountdown());
    }
}
