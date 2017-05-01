package buildings.towers;

import buildings.towers.towerupgrades.TankUpgrade;
import controllers.ProjectileController;
import projectiles.Missile;
import projectiles.Projectile;

public class Tank extends Tower{

    private static final int RADIUS = 1000;
    private static final String NAME = "TANK";
    private static final int COST = 100;
    private static final long COOLDOWN = 4000;


    public Tank(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN);
    }

    public Tower upgradeTower() {
        return new TankUpgrade((int)this.getPos().getX(), (int)this.getPos().getY());
    }

    @Override
    public Projectile makeProjectile() {
        Projectile p = new Missile(super.getTarget().getPos(), super.getPos());
        super.notifyObservers(p, "spawn");
        return p;
    }
}
