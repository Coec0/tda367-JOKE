package controllers;

import models.ProjectileModel;
import projectiles.Projectile;
import views.ProjectileView;

/**
 * Created by Emil on 2017-04-26.
 */
public class ProjectileController {
    private ProjectileModel PM;
    private ProjectileView PW;

    public ProjectileController(ProjectileModel PM, ProjectileView PW){
        this.PM = PM;
        this.PW = PW;
    }

    public void spawnProjectile(Projectile projectile){
        PM.addProjectile(projectile);
        PW.addToView(projectile);
    }

}
