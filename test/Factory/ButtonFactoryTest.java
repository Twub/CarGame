package Factory;

import FileUtilities.ImagePaths;
import org.junit.jupiter.api.Test;

import javax.swing.JButton;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ButtonFactoryTest {

    @Test
    void createTextButtonAppliesExpectedSettings() {
        JButton button = ButtonFactory.createButton("Play");

        assertEquals("Play", button.getText());
        assertFalse(button.isFocusPainted());
        assertFalse(button.isBorderPainted());
        assertFalse(button.isContentAreaFilled());
        assertNotNull(button.getCursor());
    }

    @Test
    void createIconButtonLoadsIconAndAppliesExpectedSettings() {
        JButton button = ButtonFactory.createButton(ImagePaths.PAUSE_ICON, 40, 40);

        assertNotNull(button.getIcon());
        assertFalse(button.isFocusPainted());
        assertFalse(button.isBorderPainted());
        assertFalse(button.isContentAreaFilled());
    }
}
