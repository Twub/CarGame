package GameLogic;

import GameObjects.ObsticleParticle;
import GameObjects.Player;
import GameObjects.WeaponParticle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class GameControllerTest {

    private GameController controller;
    private GameView view;

    @BeforeEach
    void setUp() {
        controller = new GameController();
        view = new GameView(controller);
        controller.setGameView(view);
        Player.getInstance().resetPosition();
    }

    @Test
    void updateMovesBackgroundOffset() {
        controller.update();

        assertEquals(4, view.getOffset());
    }

    @Test
    void updateRemovesWeaponParticlesThatLeaveScreen() {
        controller.getWeaponParticles().add(new WeaponParticle(100, 1));

        controller.update();

        assertTrue(controller.getWeaponParticles().isEmpty());
    }

    @Test
    void weaponCollisionRemovesWeaponAndObstacleAndIncreasesScore() {
        WeaponParticle weapon = new WeaponParticle(100, 100);
        ObsticleParticle obstacle = new ObsticleParticle();
        obstacle.setX(95);
        obstacle.setY(90);

        controller.getWeaponParticles().add(weapon);
        controller.getObsticleParticles().add(obstacle);

        controller.update();

        assertTrue(controller.getWeaponParticles().isEmpty());
        assertTrue(controller.getObsticleParticles().isEmpty());
    }

    @Test
    void playerCollisionSetsGameOver() {
        Player player = Player.getInstance();
        ObsticleParticle obstacle = new ObsticleParticle();
        obstacle.setX(player.getX());
        obstacle.setY(player.getY());
        controller.getObsticleParticles().add(obstacle);

        controller.update();

        assertTrue(controller.isGameOver());
        assertTrue(controller.getRestartCountdownSeconds() >= 9);
    }

    @Test
    void gameOverUpdateRestartsAfterDelay() throws Exception {
        controller.getWeaponParticles().add(new WeaponParticle(100, 100));
        controller.getObsticleParticles().add(new ObsticleParticle());
        setField(controller, "gameOver", true);
        setField(controller, "gameOverTime", System.currentTimeMillis() - 10_000);

        controller.update();

        assertFalse(controller.isGameOver());
        assertTrue(controller.getWeaponParticles().isEmpty());
        assertTrue(controller.getObsticleParticles().isEmpty());
        assertEquals(0, controller.getRestartCountdownSeconds());
    }

    private void setField(Object target, String fieldName, Object value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(target, value);
    }
}
