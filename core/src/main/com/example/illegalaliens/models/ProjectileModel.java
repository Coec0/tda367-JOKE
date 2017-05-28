package com.example.illegalaliens.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.UpdateObserver;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.models.projectiles.Projectile;
import com.example.illegalaliens.models.projectiles.ProjectileObserver;
import com.example.illegalaliens.utilities.Radar;

/**
 * Created by Emil on 2017-04-26.
 */
public class ProjectileModel implements UpdateObserver{
    private Array<Projectile> projectiles;
    private AlienModel aModel;
    private Radar radar;

    public ProjectileModel(AlienModel aModel,Radar radar){
    	this.aModel = aModel;
        projectiles = new Array<Projectile>();
        this.radar = radar;
        
    }

    @Override
    public void update(float deltaTime) {
        for (Projectile projectile : projectiles){
            move(projectile);
            if (checkIfHitEnemies(projectile)){
                projectile.damage(radar, aModel.getAllEnemies());

//                if(projectile instanceof IAOEProjectile){
//                    aoeDamage(projectile);
//                }
//                else{
//                    singleDamage(projectile);
//                }
            }
            checkForRemoval(projectile);
        }

    }

    public void addProjectile(Projectile projectile){
        projectiles.add(projectile);
        projectile.getSpriteAdapter().rotateTowards(projectile.getNewPosition(), 90);
    }

    private void move(Projectile p){
            p.setNewPosition();   
    }

    private boolean checkIfHitEnemies(Projectile projectile) {
        Array<Enemy> enemies = scan(projectile);
        return (enemies.size != 0);
    }


    private Array<Enemy> scan(Projectile projectile){
        Array<Enemy> enemies = projectile.scanEnemies(radar,projectile.getPosition(), projectile.getRadius(),aModel.getAllEnemies()); //hardcoded
        return enemies;
    }



    private boolean ifOutOfBounds(Projectile p){
        return (p.getPosition().getY() > Gdx.graphics.getHeight() && p.getPosition().getY() < 0 && p.getPosition().getX() > Gdx.graphics.getWidth() && p.getPosition().getX() < 0);
    }

    public void removeProjectileAfterHit(Projectile p){
        projectiles.removeValue(p, false);
        p = null;
    }


    private void checkForRemoval(Projectile p){
            if (ifOutOfBounds(p) || (checkIfHitEnemies(p) && p.getHealth() == 0)){
                notifyObservers(p, "remove");
            }
    }

    public void removeProjectile(Projectile p){
        projectiles.removeValue(p, false);
        p = null;
    }

    private Array<ProjectileObserver> observers = new Array<ProjectileObserver>();

    public void addObserver(ProjectileObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(ProjectileObserver observer) {
        observers.removeValue(observer, false);
    }

    private void notifyObservers(Projectile projectile, String change) {
        for (ProjectileObserver observer : observers)
            observer.actOnProjectileChange(projectile, change);
    }


}
