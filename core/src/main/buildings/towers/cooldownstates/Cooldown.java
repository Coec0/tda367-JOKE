package buildings.towers.cooldownstates;

import buildings.towers.Tower;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-04-26.
 */
public class Cooldown implements ICooldownState {
    private static final Cooldown INSTANCE = new Cooldown();

    private Cooldown(){

    }

    public static Cooldown getInstance(){
        return INSTANCE;
    }

    public void shoot(Tower tower){
    }
}
