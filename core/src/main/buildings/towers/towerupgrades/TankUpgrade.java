package buildings.towers.towerupgrades;

import buildings.towers.Tank;
import buildings.towers.Tower;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-01.
 */
public class TankUpgrade extends Tower {

    private static final int RADIUS = 1000;
    private static final String NAME = "TANK - Upgraded";
    private static final int COST = 100;
    private static final long COOLDOWN = 4000;


    public TankUpgrade(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN);
    }

    @Override
    public Projectile makeProjectile() {
        return null;
    }
}
