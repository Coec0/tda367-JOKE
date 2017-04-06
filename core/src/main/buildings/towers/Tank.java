package buildings.towers;

import projectiles.Missile;
import projectiles.Projectile;

public class Tank extends Tower{

    private static final int RADIUS = 10;
    private static final String NAME = "TANK";
    private static final int COST = 100;
    private static final int DAMAGE = 10;

    public Tank(int x, int y){
        super(x, y, RADIUS, NAME, COST, DAMAGE);
    }

    @Override
    public Projectile makeProjectile() {
        return new Missile(super.getTarget().getPos(), super.getPos());
    }
}
