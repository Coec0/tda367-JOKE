package controllers;

import buildings.BoardObject;
import towers.Tower;
import models.BoardObjectModel;
import models.ProjectileModel;
import observers.BoardObjectObserver;
import observers.ProjectileObserver;
import projectiles.Projectile;
import views.ProjectileView;

/**
 * Created by Emil on 2017-04-26.
 */
public class ProjectileController implements ProjectileObserver, BoardObjectObserver {
    private ProjectileModel PM;
    private ProjectileView PW;

    public ProjectileController(ProjectileModel PM, ProjectileView PW, BoardObjectModel BOModel) {
        this.PM = PM;
        this.PW = PW;
        PM.addObserver(this);
        BOModel.addObserver(this);
    }

    public void spawnProjectile(Projectile projectile) {
        PM.addProjectile(projectile);
        PW.addToView(projectile.getSpriteAdapter(), projectile, projectile.getRadius());
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
    public void actOnBoardObjectChange(BoardObject boardObject, boolean remove, boolean clickedOn) {
    if(boardObject instanceof Tower && !clickedOn)	// Not a good fix, should prob make another observer instead
       updateTowerObservers((Tower)boardObject, remove);
    }
}