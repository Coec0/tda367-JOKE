package models;

import com.badlogic.gdx.utils.Array;
import enemies.Enemy;
import projectiles.Projectile;
import utilities.Node;
import utilities.UpdateObserver;

/**
 * Created by Emil on 2017-04-26.
 */
public class ProjectileModel implements UpdateObserver{
    private Array<Projectile> projectiles;


    public ProjectileModel(){
        projectiles = new Array<Projectile>();
    }

    @Override
    public void update(float deltaTime) {
        moveAll();
    }

    public void addProjectile(Projectile projectile){
        projectiles.add(projectile);
    }

    public void moveAll(){
        for (Projectile p : projectiles) {
            p.setPosition(p.getNewPosition());
        }
    }

   /* public void hitEnemy(Enemy enemy){
        enemy.hurt(damage);
    }*/

}
