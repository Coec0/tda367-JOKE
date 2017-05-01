package buildings.towers.towerupgrades;

import buildings.towers.Tower;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-01.
 */
public class SoldierUpgrade extends Tower {
    private static final int RADIUS = 500;
    private static final String NAME = "SOLDIER - upgraded";
    private static final int COST = 50;
    private static final long COOLDOWN = 500;


    public SoldierUpgrade(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN);
    }

    @Override
    public Projectile makeProjectile() {
        return null;
    }
}
