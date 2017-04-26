package buildings.towers;

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
    private static final double COOLDOWN = 1;

    public Ranger(int x, int y, ProjectileController PController){
        super(x, y, RADIUS, NAME, COST, COOLDOWN,PController);
    }

    @Override
    public Projectile makeProjectile(ProjectileController PController) {
        return new RangerBullet(super.getTarget().getPos(), super.getPos());
    }
}
