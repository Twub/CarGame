package GameObjects;

import java.util.Objects;
import java.util.Random;

public class ObstacleSpawner {

    private int spawnCountdown = randomSpawnTime();

    private int randomSpawnTime() {
        // Random between 1000 and 5000 ms
        return 1000 + new Random().nextInt(4001);
    }

    public int getSpawnCountdown(){
        return spawnCountdown;
    }

    public void setRandomSpawnTime(){
        spawnCountdown = randomSpawnTime();
    }

    public void updateSpawnCountdown(int number, String calcOperation){
        if (Objects.equals(calcOperation, "+")){
            spawnCountdown += number;
        }else if (Objects.equals(calcOperation, "-")){
            spawnCountdown -= number;
        }
    }
}
