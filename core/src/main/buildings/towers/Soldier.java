package buildings.towers;

import projectiles.Bullet;
import projectiles.Projectile;

public class Soldier extends Tower {
    private static final int RADIUS = 500;
    private static final String NAME = "SOLDIER";
    private static final int COST = 50;

    public Soldier(int x, int y){
        super(x, y, RADIUS, NAME, COST);
    }

    @Override
    public Projectile makeProjectile() {
        System.out.println("makeProjectile");
        return new Bullet(super.getTarget().getPos(), super.getPos());
    }
}
