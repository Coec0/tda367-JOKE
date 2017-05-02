package buildings.towers.towerupgrades;

import buildings.towers.Tower;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-01.
 */
public class HowitzerUpgrade extends Tower {
    private static final int RADIUS = 10;
    private static final String NAME = "HOWITZER - Upgraded";
    private static final int COST = 100;
    private static final long COOLDOWN = 4000;

    public HowitzerUpgrade(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN);
    }

    @Override
    public Projectile makeProjectile() {
        return null;
    }

}
