package GameLogic;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.assertFalse;

class GameViewTest {

    @Test
    void resetForRestartClearsPressedKeyState() throws Exception {
        GameView view = new GameView(new GameController());
        setBooleanField(view, "left", true);
        setBooleanField(view, "right", true);
        setBooleanField(view, "space", true);

        view.resetForRestart();

        assertFalse(view.isLeftPressed());
        assertFalse(view.isRightPressed());
        assertFalse(view.isSpacePressed());
    }

    private void setBooleanField(Object target, String fieldName, boolean value) throws Exception {
        Field field = target.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.setBoolean(target, value);
    }
}
