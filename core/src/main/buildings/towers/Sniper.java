package buildings.towers;

import buildings.towers.towerupgrades.SniperUpgrade;
import controllers.ProjectileController;
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
    private static final long COOLDOWN = 3000;

    public Sniper(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN);
    }

    public Tower upgradeTower() {
        return new SniperUpgrade((int)this.getPos().getX(), (int)this.getPos().getY());
    }

    @Override
    public Projectile makeProjectile() {
        Projectile p = new SniperBullet(super.getTarget().getPos(), super.getPos());
        super.notifyObservers(p, "spawn");
        return p;    }

}
