package GameLogic;

import javax.swing.*;

public class GameLoop implements Runnable{

    private boolean running = true;
    private boolean paused = false;
    private GameController gameController;
    private GameView gameView;

    public GameLoop(GameView gameView, GameController gameController){
        this.gameView = gameView;
        this.gameController = gameController;
    }

    @Override
    public void run(){
        final int FRAME_PER_SECOND = 60;
        final long frameTime = 1_000_000_000L / FRAME_PER_SECOND;

        long nextFrame = System.nanoTime();

        while (running){
            if (!paused) {
                gameController.update();
            }

            // repaint must happen on Swing's EDT
            SwingUtilities.invokeLater(gameView::repaint);

            nextFrame += frameTime;

            long sleepTime = nextFrame - System.nanoTime();

            if (sleepTime > 0){
                try {
                    Thread.sleep(
                            sleepTime / 1_000_000,
                            (int)(sleepTime % 1_000_000)
                    );
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    /**
     * Switches the game between paused and running.
     */
    public void togglePause() {
        paused = !paused;
    }
}
