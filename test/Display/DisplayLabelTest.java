package Display;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DisplayLabelTest {

    @Test
    public void updateScoreIncrementsDisplayedScore() {
        DisplayLabel label = new DisplayLabel(0, 0, 50, 25, "0");

        label.updateScore();
        label.updateScore();

        assertEquals("2", label.getText());
    }

    @Test
    public void resetScoreSetsDisplayedScoreToZero() {
        DisplayLabel label = new DisplayLabel(0, 0, 50, 25, "5");

        label.resetScore();

        assertEquals("0", label.getText());
    }
}
