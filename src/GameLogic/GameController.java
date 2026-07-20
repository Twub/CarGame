package GameLogic;

import Factory.PlayerFactory;
import FileUtilities.SoundPaths;
import FileUtilities.SoundUtility;
import GameObjects.ObstacleSpawner;
import GameObjects.ObsticleParticle;
import GameObjects.Player;
import GameObjects.WeaponParticle;

import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Coordinates the main game state updates for movement, shooting, enemy spawning,
 * and projectile collisions.
 */
public class GameController {
    // Background scroll state.
    private GameView gameView;
    private int speed = 4;

    // Player state and movement speed.
    private Player player = PlayerFactory.createPlayer();
    private int playerSpeed = 2;
    private boolean gameOver = false;
    private long gameOverTime = 0;
    private static final long GAME_OVER_RESTART_DELAY = 10_000;

    // Active weapon particles and the cooldown used to limit the fire rate.
    private ArrayList<WeaponParticle> weaponParticles = new ArrayList<>();
    private long lastShotTime = 0;
    private static final long SHOT_COOLDOWN = 250; // milliseconds

    // Active obstacle particles and the spawner that controls when new ones appear.
    private ArrayList<ObsticleParticle> obsticleParticles = new ArrayList<>();
    private ObstacleSpawner enemySpawner = new ObstacleSpawner();

    /**
     * Advances the game by one tick.
     */
    public void update(){
        if (gameOver) {
            if (System.currentTimeMillis() - gameOverTime >= GAME_OVER_RESTART_DELAY) {
                restartGame();
            }
            return;
        }

        // Scroll the background and wrap it back to the top when one full image has moved.
        int h = gameView.getBackgroundHeight();
        int offset = gameView.getOffset();
        gameView.setOffset(offset + speed);
        if (gameView.getOffset() >= h){
            gameView.setOffset(0);
        }

        // Move the player left while keeping the car inside the road bounds.
        if (gameView.isLeftPressed()) {
            if (player.getX() > 63) {
                player.updatePosition(player.getX() - playerSpeed);
            }else {
                SoundUtility.play(SoundPaths.BOUNCE_SOUND);
            }
        }

        // Move the player right while keeping the car inside the road bounds.
        if (gameView.isRightPressed()) {
            if (player.getX() < 543) {
                player.updatePosition(player.getX() + playerSpeed);
            }else {
                SoundUtility.play(SoundPaths.BOUNCE_SOUND);
            }
        }

        // Fire a weapon particle only if the cooldown has expired.
        if (gameView.isSpacePressed()) {
            long currentTime = System.currentTimeMillis();

            if (currentTime - lastShotTime >= SHOT_COOLDOWN) {
                WeaponParticle particle = PlayerFactory.createWeaponParticle(
                        player.getX() + player.getPlayerImage().getWidth(null) / 2,
                        player.getY() - 10
                );

                weaponParticles.add(particle);
                SoundUtility.play(SoundPaths.WEAPON_FIRE_SOUND);

                lastShotTime = currentTime;
            }
        }

        // Update shots from the end of the list so expired particles can be removed safely.
        if (!weaponParticles.isEmpty()){
            for (int i = weaponParticles.size() - 1; i >= 0; i--){
                WeaponParticle particle = weaponParticles.get(i);
                particle.updatePosition();
                if (particle.getY() <= 0){
                    weaponParticles.remove(i);
                }
            }
        }

        // Count down enemy spawning and reset the timer after each spawn.
        enemySpawner.updateSpawnCountdown(30, "-");
        if (enemySpawner.getSpawnCountdown() <= 0){
            spawnEnemy();
            enemySpawner.setRandomSpawnTime();
        }

        // Move obstacles downward and remove any that have left the visible play area.
        if (!obsticleParticles.isEmpty()){
            for (int i = obsticleParticles.size() - 1; i >= 0; i--){
                ObsticleParticle obstacle = obsticleParticles.get(i);
                obstacle.updatePosition();
                if (obstacle.getY() > 1000 + obstacle.getDiameter()){
                    obsticleParticles.remove(obstacle);
                }
            }
        }

        if (playerCollision()) {
            gameOver = true;
            gameOverTime = System.currentTimeMillis();
            return;
        }

        // Check projectile-obstacle collisions only when both lists have active particles.
        if (!obsticleParticles.isEmpty() && !weaponParticles.isEmpty()){
            weaponCollision();
        }
    }

    /**
     * Creates a new obstacle and adds it to the active obstacle list.
     */
    private void spawnEnemy(){
        ObsticleParticle particle = PlayerFactory.createObsticleParticle();
        obsticleParticles.add(particle);
        //System.out.println("X: " + particle.getX() + " - " + "Y: " + particle.getY());
    }

    /**
     * Resets the gameplay state after the game-over delay has finished.
     */
    private void restartGame() {
        weaponParticles.clear();
        obsticleParticles.clear();
        enemySpawner = new ObstacleSpawner();
        lastShotTime = 0;
        gameOverTime = 0;
        gameOver = false;

        player.resetPosition();
        gameView.resetForRestart();
    }

    /**
     * Removes both particles when a weapon particle hits an obstacle.
     */
    private void weaponCollision(){
        for (int i = weaponParticles.size() - 1; i >= 0; i--) {
            WeaponParticle weapon = weaponParticles.get(i);

            for (int j = obsticleParticles.size() - 1; j >= 0; j--) {
                ObsticleParticle obstacle = obsticleParticles.get(j);

                if (weaponHitsObstacle(weapon, obstacle)) {
                    weaponParticles.remove(i);
                    obsticleParticles.remove(j);
                    gameView.updateScoreboard();
                    break;
                }
            }
        }
    }

    /**
     * Returns true when the player's rendered bounds hit any active obstacle.
     */
    private boolean playerCollision() {
        Rectangle playerBounds = new Rectangle(
                player.getX(),
                player.getY(),
                player.getPlayerImage().getWidth(null),
                player.getPlayerImage().getHeight(null)
        );

        for (ObsticleParticle obstacle : obsticleParticles) {
            Rectangle obstacleBounds = new Rectangle(
                    obstacle.getX(),
                    obstacle.getY(),
                    obstacle.getWidth(),
                    obstacle.getHeight()
            );

            if (playerBounds.intersects(obstacleBounds)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks a circular weapon particle against the rendered obstacle bounds.
     */
    private boolean weaponHitsObstacle(WeaponParticle weapon, ObsticleParticle obstacle) {
        double weaponCenterX = weapon.getX() + weapon.getRadius();
        double weaponCenterY = weapon.getY() + weapon.getRadius();

        int obstacleWidth = obstacle.getWidth();
        int obstacleHeight = obstacle.getHeight();

        double closestX = clamp(weaponCenterX, obstacle.getX(), obstacle.getX() + obstacleWidth);
        double closestY = clamp(weaponCenterY, obstacle.getY(), obstacle.getY() + obstacleHeight);

        double dx = weaponCenterX - closestX;
        double dy = weaponCenterY - closestY;

        return dx * dx + dy * dy <= weapon.getRadius() * weapon.getRadius();
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(value, max));
    }

    /**
     * Returns the active weapon particles so the view can draw them.
     */
    public ArrayList<WeaponParticle> getWeaponParticles(){
        return weaponParticles;
    }

    /**
     * Returns the active obstacle particles so the view can draw them.
     */
    public ArrayList<ObsticleParticle> getObsticleParticles(){
        return obsticleParticles;
    }

    /**
     * Returns true after the player has collided with an obstacle.
     */
    public boolean isGameOver() {
        return gameOver;
    }

    /**
     * Returns the number of seconds left before the game restarts.
     */
    public int getRestartCountdownSeconds() {
        if (!gameOver) {
            return 0;
        }

        long elapsed = System.currentTimeMillis() - gameOverTime;
        long remaining = Math.max(0, GAME_OVER_RESTART_DELAY - elapsed);
        return (int) Math.ceil(remaining / 1000.0);
    }

    /**
     * Connects this controller to the view it updates and reads input from.
     */
    public void setGameView(GameView gameView) {
        this.gameView = gameView;
    }
}
