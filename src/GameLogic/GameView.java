package GameLogic;

import Display.DisplayLabel;
import Factory.ButtonFactory;
import Factory.DisplayFactory;
import Factory.DisplayTypes;
import Factory.PlayerFactory;
import FileUtilities.ImagePaths;
import FileUtilities.ImageUtility;
import GameObjects.ObsticleParticle;
import GameObjects.Player;
import GameObjects.WeaponParticle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Swing panel responsible for drawing the game scene and tracking keyboard input.
 */
public class GameView extends JPanel {

    private GameController gameController;

    public GameView(GameController gameController){
        // Configure the panel before registering input handlers.
        setLayout(null);
        setBackground(Color.YELLOW);
        setupKeyBindings();
        setupPlayButton();
        setupPauseButton();
        setupScoreboard();
        this.gameController = gameController;
    }

    // Scrolling background image and the current vertical draw offset.
    private static final Image BACKGROUND_IMAGE = ImageUtility.resizeLoadImage(ImagePaths.BACKGROUND, 667, 1000);
    private int offset = 0;

    // Local player image data and key states read by the controller.
    private Player player = PlayerFactory.createPlayer();
    private boolean left, right;
    private boolean space;
    private JButton playButton;
    private JButton pauseButton;
    private DisplayLabel scoreboard;

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw two copies of the background so scrolling appears continuous.
        int h = BACKGROUND_IMAGE.getHeight(this) - 3;
        g.drawImage(BACKGROUND_IMAGE, 0, offset, this);
        g.drawImage(BACKGROUND_IMAGE, 0, offset - h, this);

        // Draw the player car at its current position.
        g.drawImage(player.getPlayerImage(), player.getX(), player.getY(), this);

        // Draw every active weapon particle.
        for (WeaponParticle particle : gameController.getWeaponParticles()) {
            if (particle.getY() >= 0) {
                //g.setColor(particle.getParticleColor());
                //g.fillOval(particle.getX(), particle.getY(), particle.getDiameter(), particle.getDiameter());
                g.drawImage(particle.getParticleImage(), particle.getX(), particle.getY(), this);
            }
        }

        // Daw every active obsticlae
        for (ObsticleParticle  obsticleParticle : gameController.getObsticleParticles()){
            //g.setColor(obsticleParticle.getParticleColor());
            //g.fillOval(obsticleParticle.getX(), obsticleParticle.getY(), obsticleParticle.getDiameter(), obsticleParticle.getDiameter());
            g.drawImage(obsticleParticle.getParticleImage(), obsticleParticle.getX(), obsticleParticle.getY(), this);
        }

        if (gameController.isGameOver()) {
            drawGameOver(g);
        }
    }

    private void drawGameOver(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setColor(new Color(0, 0, 0, 170));
        g2.fillRect(0, 0, getWidth(), getHeight());

        g2.setFont(new Font("Arial", Font.BOLD, 64));

        String gameOverText = "GAME OVER!!!";
        FontMetrics gameOverMetrics = g2.getFontMetrics();
        int gameOverX = (getWidth() - gameOverMetrics.stringWidth(gameOverText)) / 2;
        int gameOverY = (getHeight() - gameOverMetrics.getHeight()) / 2 + gameOverMetrics.getAscent();

        g2.setColor(Color.RED);
        g2.drawString(gameOverText, gameOverX, gameOverY);

        g2.setFont(new Font("Arial", Font.BOLD, 32));
        String restartText = "Restart in: " + gameController.getRestartCountdownSeconds() + "s";
        FontMetrics restartMetrics = g2.getFontMetrics();
        int restartX = (getWidth() - restartMetrics.stringWidth(restartText)) / 2;
        int restartY = gameOverY + restartMetrics.getHeight() + 20;

        g2.setColor(Color.WHITE);
        g2.drawString(restartText, restartX, restartY);
        g2.dispose();
    }

    /**
     * Sets the vertical background offset used for scrolling.
     */
    public void setOffset(int offset){
        this.offset = offset;
    }

    /**
     * Returns the current vertical background offset.
     */
    public int getOffset(){
        return offset;
    }

    /**
     * Returns the background image height used by the controller for wrapping.
     */
    public int getBackgroundHeight(){
        return BACKGROUND_IMAGE.getHeight(this);
    }

    /**
     * Creates the play button shown before the game loop starts.
     */
    private void setupPlayButton() {
        playButton = ButtonFactory.createButton("Play");
        playButton.setBounds(258, 340, 150, 45);
        add(playButton);
    }

    /**
     * Creates the pause button in the top-left corner.
     */
    private void setupPauseButton() {
        pauseButton = ButtonFactory.createButton(ImagePaths.PAUSE_ICON, 40, 40);
        pauseButton.setBounds(10, 10, 40, 40);
        pauseButton.setEnabled(false);
        add(pauseButton);
    }

    /**
     * Creates the scoreboard in the upper-left corner.
     */
    private void setupScoreboard() {
        scoreboard = (DisplayLabel) DisplayFactory.createDisplay(DisplayTypes.LABEL, 600, 5, 50, 25, "0");
        add(scoreboard);
    }

    /**
     * Updates the score displayed by the scoreboard label.
     */
    public void updateScoreboard() {
        scoreboard.updateScore();
    }

    /**
     * Clears view state when the controller starts a new round.
     */
    public void resetForRestart() {
        left = false;
        right = false;
        space = false;
        scoreboard.resetScore();
    }

    /**
     * Registers the action that starts the game loop when the play button is clicked.
     */
    public void setPlayButtonAction(Runnable playAction) {
        playButton.addActionListener(e -> {
            playButton.setVisible(false);
            pauseButton.setEnabled(true);
            playAction.run();
            requestFocusInWindow();
        });
    }

    /**
     * Registers the action that pauses or resumes the game loop.
     */
    public void setPauseButtonAction(Runnable pauseAction) {
        pauseButton.addActionListener(e -> {
            pauseAction.run();
            requestFocusInWindow();
        });
    }

    /**
     * Maps key press and release events to boolean input states.
     */
    private void setupKeyBindings() {
        InputMap inputMap = getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap actionMap = getActionMap();

        // Track whether the left arrow key is currently held down.
        inputMap.put(KeyStroke.getKeyStroke("pressed LEFT"), "leftPressed");
        inputMap.put(KeyStroke.getKeyStroke("released LEFT"), "leftReleased");

        actionMap.put("leftPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                left = true;
            }
        });

        actionMap.put("leftReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                left = false;
            }
        });

        // Track whether the right arrow key is currently held down.
        inputMap.put(KeyStroke.getKeyStroke("pressed RIGHT"), "rightPressed");
        inputMap.put(KeyStroke.getKeyStroke("released RIGHT"), "rightReleased");

        actionMap.put("rightPressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                right = true;
            }
        });

        actionMap.put("rightReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                right = false;
            }
        });

        // Track whether the space key is currently held down.
        inputMap.put(KeyStroke.getKeyStroke("pressed SPACE"), "spacePressed");
        inputMap.put(KeyStroke.getKeyStroke("released SPACE"), "spaceReleased");

        actionMap.put("spacePressed", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                space = true;
            }
        });

        actionMap.put("spaceReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                space = false;
            }
        });
    }

    /**
     * Returns true while the left arrow key is pressed.
     */
    public boolean isLeftPressed() {
        return left;
    }

    /**
     * Returns true while the right arrow key is pressed.
     */
    public boolean isRightPressed() {
        return right;
    }

    /**
     * Returns true while the space key is pressed.
     */
    public boolean isSpacePressed(){
        return space;
    }

}
