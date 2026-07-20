package GameObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class WeaponParticleTest {

    @Test
    void constructorSetsExpectedDefaults() {
        WeaponParticle weapon = new WeaponParticle(120, 300);

        assertEquals(120, weapon.getX());
        assertEquals(300, weapon.getY());
        assertEquals(10, weapon.getDiameter());
        assertEquals(7, weapon.getSpeed());
        assertEquals(5.0, weapon.getRadius());
        assertNotNull(weapon.getParticleColor());
        assertNotNull(weapon.getParticleImage());
    }

    @Test
    void updatePositionMovesWeaponUp() {
        WeaponParticle weapon = new WeaponParticle(120, 300);

        weapon.updatePosition();

        assertEquals(293, weapon.getY());
    }
}
