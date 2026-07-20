package Factory;

import FileUtilities.ImagePaths;
import FileUtilities.ImageUtility;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public final class ButtonFactory {

    public static JButton createButton(String text){
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();

                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING,
                        RenderingHints.VALUE_ANTIALIAS_ON
                );

                // Background
                g2.setColor(getModel().isPressed()
                        ? new Color(30, 90, 180)
                        : getModel().isRollover()
                          ? new Color(60, 130, 230)
                          : new Color(40, 110, 220));

                g2.fillRoundRect(
                        0, 0,
                        getWidth(),
                        getHeight(),
                        20, 20
                );

                g2.dispose();

                super.paintComponent(g);
            }
        };

        button.setFont(new Font("Segoe UI", Font.BOLD, 14));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        button.setPreferredSize(new Dimension(250, 40));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }

    public static JButton createButton(ImagePaths imagePaths, int width, int height){
        ImageIcon buttonIcon = new ImageIcon(Objects.requireNonNull(ImageUtility.resizeLoadImage(imagePaths, width, height)));
        JButton button = new JButton(buttonIcon);

        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);
        button.setMargin(new Insets(0, 0, 0, 0));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        return button;
    }
}
