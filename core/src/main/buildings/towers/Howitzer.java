package buildings.towers;

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

    public Howitzer(int x, int y, ProjectileController PController){
        super(x, y, RADIUS, NAME, COST, COOLDOWN ,PController);
    }

    @Override
    public Projectile makeProjectile(ProjectileController PController) {
        return new ArtilleryRound(super.getTarget().getPos(), super.getPos());
    }
}
