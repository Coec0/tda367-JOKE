package models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import projectiles.IAOEProjectile;
import projectiles.Projectile;
import utilities.ProjectileObserver;
import utilities.Radar;
import utilities.UpdateObserver;

/**
 * Created by Emil on 2017-04-26.
 */
public class ProjectileModel implements UpdateObserver{
    private Array<Projectile> projectiles;
    private AlienModel aModel;
    private Radar radar;

    public ProjectileModel(AlienModel aModel){
    	this.aModel = aModel;
        projectiles = new Array<Projectile>();
        this.radar = new Radar();
        
    }

    @Override
    public void update(float deltaTime) {
        for (Projectile projectile : projectiles){
            move(projectile);
            if (checkIfHitEnemies(projectile)){
                if(projectile instanceof IAOEProjectile){
                    aoeDamage(projectile);
                }
                else{
                    singleDamage(projectile);
                }
            }
            checkForRemoval(projectile);
        }

    }

    public void addProjectile(Projectile projectile){
        projectiles.add(projectile);
        projectile.getSpriteAdapter().rotateTowards(projectile.getNewPosition(), 90);
    }

    public void move(Projectile p){
            p.setNewPosition();   
    }

    public boolean checkIfHitEnemies(Projectile projectile) {
        Array<Enemy> enemies = scan(projectile);
        return (enemies.size != 0);
    }


    public Array<Enemy> scan(Projectile projectile){
        Array<Enemy> enemies = radar.scan(projectile.getPosition(), projectile.getRadius(),aModel.getAllAliens()); //hardcoded
        return enemies;
    }

    public Array<Enemy> aoeScan(Projectile projectile){
        Array<Enemy> enemies = radar.scan(projectile.getPosition(), ((IAOEProjectile) projectile).getAOERadius(),aModel.getAllAliens()); //hardcoded
        return enemies;
    }



    public void aoeDamage(Projectile projectile){
        Array<Enemy> enemies = aoeScan(projectile);
        for (Enemy enemy: enemies){
        enemy.hurt(projectile.getDamage());
        }
        projectile.reduceHealth();
    }

    public void singleDamage(Projectile projectile){
        Enemy enemy = scan(projectile).first();
        enemy.hurt(projectile.getDamage());
        projectile.reduceHealth();
    }



    public boolean ifOutOfBounds(Projectile p){
        return (p.getPosition().getY() > Gdx.graphics.getHeight() && p.getPosition().getY() < 0 && p.getPosition().getX() > Gdx.graphics.getWidth() && p.getPosition().getX() < 0);
    }

    public void removeProjectileAfterHit(Projectile p){
        projectiles.removeValue(p, false);
        p = null;
    }


    public void checkForRemoval(Projectile p){
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
