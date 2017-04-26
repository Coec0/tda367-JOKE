package buildings.towers.cooldownstates;

import buildings.towers.Tower;
import controllers.ProjectileController;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-04-26.
 */
public class NoCooldown implements ICooldownState {
    private static final NoCooldown INSTANCE = new NoCooldown();

    private NoCooldown(){

    }

    public static NoCooldown getInstance(){
        return INSTANCE;
    }

    public void shoot(Tower tower){
        tower.setCooldownState(Cooldown.getInstance());
        tower.startCooldown();
        tower.makeProjectile(tower.getPController());
    }
}
