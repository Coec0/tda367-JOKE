package buildings.towers;

import buildings.towers.towerupgrades.SoldierUpgrade;
import controllers.ProjectileController;
import projectiles.Bullet;
import projectiles.Missile;
import projectiles.Projectile;

public class Soldier extends Tower {
    private static final int RADIUS = 500;
    private static final String NAME = "SOLDIER";
    private static final int COST = 50;
    private static final long COOLDOWN = 500;


    public Soldier(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN);
    }


    public Tower upgradeTower() {
        return new SoldierUpgrade((int)this.getPos().getX(), (int)this.getPos().getY());
    }

    @Override
    public Projectile makeProjectile() {
        Projectile p = new Bullet(super.getTarget().getPos(), super.getPos());
        super.notifyObservers(p, "spawn");
        return p;    }
}
