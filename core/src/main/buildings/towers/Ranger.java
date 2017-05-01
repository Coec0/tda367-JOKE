package buildings.towers;

import buildings.towers.towerupgrades.RangerUpgrade;
import controllers.ProjectileController;
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
    private static final long COOLDOWN = 1000;

    public Ranger(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN);
    }

    public Tower upgradeTower() {
        return new RangerUpgrade((int)this.getPos().getX(), (int)this.getPos().getY());
    }

    @Override
    public Projectile makeProjectile() {
        Projectile p = new RangerBullet(super.getTarget().getPos(), super.getPos());
        super.notifyObservers(p, "spawn");
        return p;    }
}
