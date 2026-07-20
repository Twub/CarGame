package GameObjects;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

class PlayerTest {

    @Test
    void getInstanceReturnsSingletonPlayer() {
        assertSame(Player.getInstance(), Player.getInstance());
    }

    @Test
    void resetPositionRestoresStartingPosition() {
        Player player = Player.getInstance();
        player.updatePosition(100);

        player.resetPosition();

        assertEquals(800, player.getY());
        assertEquals(667 / 2 - player.getPlayerImage().getWidth(null) / 2, player.getX());
    }

    @Test
    void playerImageIsLoaded() {
        assertNotNull(Player.getInstance().getPlayerImage());
    }
}
