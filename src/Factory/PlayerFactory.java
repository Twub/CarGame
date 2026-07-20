package Factory;

import GameObjects.ObsticleParticle;
import GameObjects.Player;
import GameObjects.WeaponParticle;

public final class PlayerFactory {

    public static Player createPlayer(){
        Player p = Player.getInstance();
        return p;
    }

    public static WeaponParticle createWeaponParticle(int playerX, int playerY){
        WeaponParticle weaponParticle = new WeaponParticle(playerX, playerY);
        return weaponParticle;
    }

    public static ObsticleParticle createObsticleParticle(){
        ObsticleParticle obsticleParticle = new ObsticleParticle();
        return obsticleParticle;
    }
}
