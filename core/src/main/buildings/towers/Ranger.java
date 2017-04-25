package buildings.towers;

import projectiles.Projectile;
import projectiles.RangerBullet;
import projectiles.SniperBullet;

/**
 * Created by Emil on 2017-04-17.
 */
public class Ranger extends Tower {
    private static final int RADIUS = 750;
    private static final String NAME = "RANGER";
    private static final int COST = 300;

    public Ranger(int x, int y){
        super(x, y, RADIUS, NAME, COST);
    }

    @Override
    public Projectile makeProjectile() {
        return new RangerBullet(super.getTarget().getPos(), super.getPos());
    }
}
