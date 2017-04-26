package models;

import com.badlogic.gdx.utils.Array;
import enemies.Enemy;
import projectiles.Projectile;
import utilities.Node;
import utilities.Radar;
import utilities.UpdateObserver;

/**
 * Created by Emil on 2017-04-26.
 */
public class ProjectileModel implements UpdateObserver{
    private Array<Projectile> projectiles;

    private Radar radar;

    public ProjectileModel(Radar radar){
        projectiles = new Array<Projectile>();
        this.radar = radar;
        
    }

    @Override
    public void update(float deltaTime) {
        moveAll();
        checkIfHitEnemy();
    }

    public void addProjectile(Projectile projectile){
        projectiles.add(projectile);
    }

    public void moveAll(){
        for (Projectile p : projectiles) {
            p.setSpritePosition(p.getNewPosition());
        }
    }

    public void checkIfHitEnemy(){
        for (Projectile projectile : projectiles) {
            Array<Enemy> enemies = radar.scan(projectile.getPosition(), projectile.getRadius()); //hardcoded
            if (enemies.size != 0) {
                for (Enemy enemy : enemies) {
                    enemy.hurt(projectile.getDamage());
                    System.out.println(enemy.getHealth());
                }
            }
        }
    }


}
