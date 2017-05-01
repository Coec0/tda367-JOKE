package buildings.towers;

import buildings.towers.towerupgrades.HowitzerUpgrade;
import controllers.ProjectileController;
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
    private static final long COOLDOWN = 4000;

    public Howitzer(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN );
    }

    public Tower upgradeTower() {
        return new HowitzerUpgrade((int)this.getPos().getX(), (int)this.getPos().getY());
    }

    @Override
    public Projectile makeProjectile() {
        Projectile p = new ArtilleryRound(super.getTarget().getPos(), super.getPos());
        super.notifyObservers(p, "spawn");
        return p;    }
}
