package buildings.towers;

import projectiles.ArtilleryRound;
import projectiles.Missile;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-04-17.
 */
public class Howitzer extends Tower {
    private static final int RADIUS = 10;
    private static final String NAME = "HOWITZER";
    private static final int COST = 100;

    public Howitzer(int x, int y){
        super(x, y, RADIUS, NAME, COST);
    }

    @Override
    public Projectile makeProjectile() {
        return new ArtilleryRound(super.getTarget().getPos(), super.getPos());
    }
}
