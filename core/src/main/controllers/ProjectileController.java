package controllers;

import buildings.Building;
import buildings.towers.Tower;
import models.BuildingModel;
import models.ProjectileModel;
import projectiles.Projectile;
import utilities.BuildingObserver;
import utilities.ProjectileObserver;
import views.ProjectileView;

/**
 * Created by Emil on 2017-04-26.
 */
public class ProjectileController implements ProjectileObserver, BuildingObserver {
    private ProjectileModel PM;
    private ProjectileView PW;

    public ProjectileController(ProjectileModel PM, ProjectileView PW, BuildingModel BM) {
        this.PM = PM;
        this.PW = PW;
        PM.addObserver(this);
        BM.addObserver(this);
    }

    public void spawnProjectile(Projectile projectile) {
        PM.addProjectile(projectile);
        PW.addToView(projectile);
    }

    public void removeProjectile(Projectile projectile) {
        PM.removeProjectile(projectile);
        PW.removeFromView(projectile.getSpriteAdapter());
    }

    public void updateTowerObservers(Tower tower, boolean remove) {
            if(!remove){
                tower.addObserver(this);

            }
            else{
                tower.removeObserver(this);
            }

    }
        @Override
        public void actOnProjectileChange (Projectile projectile, String change){
            if (change.equals("spawn")) {
                spawnProjectile(projectile);
            } else if (change.equals("remove")) {
                removeProjectile(projectile);
            }
        }


    @Override
    public void actOnBuildingChange(Building building, boolean remove, boolean clickedOn) {
    if(building instanceof Tower && !clickedOn)	// Not a good fix, should prob make another observer instead
       updateTowerObservers((Tower)building, remove);
    }
}