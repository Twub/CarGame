package Factory;

import GameObjects.ObsticleParticle;
import GameObjects.Player;
import GameObjects.WeaponParticle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class PlayerFactoryTest {

    @Test
    void createPlayerReturnsSingletonPlayer() {
        assertSame(Player.getInstance(), PlayerFactory.createPlayer());
    }

    @Test
    void createWeaponParticleUsesGivenPosition() {
        WeaponParticle weapon = PlayerFactory.createWeaponParticle(50, 70);

        assertEquals(50, weapon.getX());
        assertEquals(70, weapon.getY());
    }

    @Test
    void createObsticleParticleReturnsObstacle() {
        ObsticleParticle obstacle = PlayerFactory.createObsticleParticle();

        assertNotNull(obstacle);
    }
}
