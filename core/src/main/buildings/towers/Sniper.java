package buildings.towers;


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
    private static final int COOLDOWN = 10;

    public Sniper(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN);
    }

   

    @Override
    public Projectile makeProjectile() {
        Projectile p = new SniperBullet(super.getTarget().getPos(), super.getPos());
        super.notifyObservers(p, "spawn");
        return p;    }

}
