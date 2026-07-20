package Display;

import javax.swing.*;
import java.awt.*;

public class DisplayLabel extends JLabel {

    private int xPosition;
    private int yPosition;
    private int width;
    private int height;
    private String text;

    public DisplayLabel(int xPosition, int yPosition, int width, int height, String text){
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.width = width;
        this.height = height;
        this.text = text;

        setBounds(this.xPosition, this.yPosition, this.width, this.height);
        setText(this.text);
        setFont(new Font("Segoe UI", Font.BOLD, 20));
        setForeground(Color.WHITE);
    }

    public void updateScore(){
        int oldScore = Integer.parseInt(text);
        int newScore = oldScore + 1;
        text = Integer.toString(newScore);
        setText(text);
    }

    public void resetScore() {
        text = "0";
        setText(text);
    }
}
