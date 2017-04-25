package buildings.towers;

import projectiles.Missile;
import projectiles.Projectile;
import projectiles.SniperBullet;

/**
 * Created by Emil on 2017-04-17.
 */
public class Sniper extends Tower {


    private static final int RADIUS = 100;
    private static final String NAME = "SNIPER";
    private static final int COST = 150;

    public Sniper(int x, int y){
        super(x, y, RADIUS, NAME, COST);
    }

    @Override
    public Projectile makeProjectile() {
        return new SniperBullet(super.getTarget().getPos(), super.getPos());
    }

}
