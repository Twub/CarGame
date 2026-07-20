import GameLogic.GameController;
import GameLogic.GameLoop;
import GameLogic.GameView;

import javax.swing.*;
import java.util.concurrent.atomic.AtomicBoolean;

private static final String OS_NAME = System.getProperty("os.name");
private static final int WINDOW_WIDTH = 667; //350;
private static final int WINDOW_HEIGHT = 1000; //700;

void main() {

    JFrame window = new JFrame();
    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    window.setTitle("CarGame");
    window.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
    window.setLayout(null);
    window.setLocationRelativeTo(null);

    GameController controller = new GameController();

    GameView view = new GameView(controller);
    controller.setGameView(view);
    view.setBounds(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    window.add(view);
    GameLoop gameLoop = new GameLoop(view, controller);
    AtomicBoolean gameStarted = new AtomicBoolean(false);

    view.setPlayButtonAction(() -> {
        if (gameStarted.compareAndSet(false, true)) {
            Thread gameThread = new Thread(gameLoop);
            gameThread.start();
        }
    });
    view.setPauseButtonAction(gameLoop::togglePause);

    window.setVisible(true);
}
