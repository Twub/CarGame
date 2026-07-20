package Factory;

import Display.DisplayLabel;

import javax.swing.*;

public abstract class DisplayFactory {

    public static JComponent createDisplay(DisplayTypes type, int x, int y, int width, int height, String text){
        switch (type){
            case LABEL -> {
                return new DisplayLabel(x, y, width, height, text);
            }

        }
        return null;
    }

}
