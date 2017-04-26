package buildings.towers;

import controllers.ProjectileController;
import projectiles.Missile;
import projectiles.Projectile;

public class Tank extends Tower{

    private static final int RADIUS = 10;
    private static final String NAME = "TANK";
    private static final int COST = 100;

    public Tank(int x, int y, ProjectileController PController){
        super(x, y, RADIUS, NAME, COST, PController);
    }

    @Override
    public Projectile makeProjectile(ProjectileController PController) {
        Projectile projectile = new Missile(super.getTarget().getPos(), super.getPos());
        PController.spawnProjectile(projectile);
        return projectile;
    }
}
