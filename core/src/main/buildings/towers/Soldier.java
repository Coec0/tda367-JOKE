package buildings.towers;

import controllers.ProjectileController;
import projectiles.Bullet;
import projectiles.Missile;
import projectiles.Projectile;

public class Soldier extends Tower {
    private static final int RADIUS = 500;
    private static final String NAME = "SOLDIER";
    private static final int COST = 50;

    public Soldier(int x, int y, ProjectileController PController){
        super(x, y, RADIUS, NAME, COST, PController);
    }

    @Override
    public Projectile makeProjectile(ProjectileController PController) {
        Projectile projectile = new Bullet(super.getTarget().getPos(), super.getPos());
        PController.spawnProjectile(projectile);
        return projectile;
    }
}
