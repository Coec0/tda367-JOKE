package controllers;

import buildings.Building;
import buildings.towers.Tower;
import models.BuildingModel;
import models.ProjectileModel;
import projectiles.Projectile;
import utilities.BuildingObserver;
import utilities.ProjectileObserver;
import utilities.UpdateObserver;
import views.ProjectileView;

/**
 * Created by Emil on 2017-04-26.
 */
public class ProjectileController implements ProjectileObserver, BuildingObserver {
    private ProjectileModel PM;
    private ProjectileView PW;
    private BuildingModel BM;

    public ProjectileController(ProjectileModel PM, ProjectileView PW, BuildingModel BM) {
        this.PM = PM;
        this.PW = PW;
        this.BM = BM;
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

    public void updateTowerObservers(boolean remove) {
        for (Tower tower : BM.getTowers()) {
            if(!remove){
                tower.addObserver(this);

            }
            else{
                tower.removeObserver(this);
            }

        }

    }
        @Override
        public void actOnProjectileChange (Projectile projectile, String change){
            if (change.equals("spawn")) {
                spawnProjectile(projectile);
                System.out.println("Spawnp");
            } else if (change.equals("remove")) {
                removeProjectile(projectile);
            }
        }


    @Override
    public void actOnBuildingChange(Building building, boolean remove) {
       updateTowerObservers(remove);
    }
}