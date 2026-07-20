package Factory;

import Display.DisplayLabel;
import org.junit.jupiter.api.Test;

import javax.swing.JComponent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class DisplayFactoryTest {

    @Test
    void createDisplayCreatesLabelForLabelType() {
        JComponent component = DisplayFactory.createDisplay(DisplayTypes.LABEL, 1, 2, 3, 4, "7");

        DisplayLabel label = assertInstanceOf(DisplayLabel.class, component);
        assertEquals("7", label.getText());
        assertEquals(1, label.getX());
        assertEquals(2, label.getY());
        assertEquals(3, label.getWidth());
        assertEquals(4, label.getHeight());
    }
}
