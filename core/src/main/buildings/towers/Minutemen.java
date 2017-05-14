package buildings.towers;

import factories.ProjectileFactory;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-14.
 */
public class Minutemen extends Tower {

    private static final int RADIUS = 1000;
    private static final String NAME = "MINUTEMEN";
    private static final int COST = 50;
    private static final int COOLDOWN = 50;
    private static String DESCRIPTION = "Temporary soldiers with high range but low rate of fire";
    private static final float SIZE = 50;
    private static final float DAMAGE = 50;
    private static final float SPEED = 50;


    public Minutemen(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN, SIZE, DAMAGE);
    }

    public String getDescription(){
        return DESCRIPTION;
    }

    @Override
    public Projectile makeProjectile() {
        Projectile p = ProjectileFactory.createBullet(super.getTarget().getPos(), super.getPos(), DAMAGE, SPEED);
        super.notifyObservers(p, "spawn");
        return p;    }

}
