package buildings.towers.towerupgrades;

import buildings.towers.Tower;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-01.
 */
public class RangerUpgrade extends Tower {
    private static final int RADIUS = 750;
    private static final String NAME = "RANGER - Upgraded";
    private static final int COST = 300;
    private static final long COOLDOWN = 1000;


    public RangerUpgrade(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN);
    }

    @Override
    public Projectile makeProjectile() {
        return null;
    }
}
