package buildings.towers;

import controllers.ProjectileController;
import projectiles.Missile;
import projectiles.Projectile;

public class Tank extends Tower{

    private static final int RADIUS = 10;
    private static final String NAME = "TANK";
    private static final int COST = 100;
    private static final long COOLDOWN = 4000;


    public Tank(int x, int y, ProjectileController PController){
        super(x, y, RADIUS, NAME, COST, COOLDOWN ,PController);
    }

    @Override
    public Projectile makeProjectile(ProjectileController PController) {
        Projectile projectile = new Missile(super.getTarget().getPos(), super.getPos());
        PController.spawnProjectile(projectile);
        return projectile;
    }
}
