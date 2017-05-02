package buildings.towers.towerupgrades;

import buildings.towers.Tower;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-01.
 */
public class SniperUpgrade extends Tower {
    private static final int RADIUS = 100;
    private static final String NAME = "SNIPER - Upgraded";
    private static final int COST = 150;
    private static final long COOLDOWN = 3000;


    public SniperUpgrade(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN);
    }

    @Override
    public Projectile makeProjectile() {
        return null;
    }

}
