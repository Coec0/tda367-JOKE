package projectiles;

import com.badlogic.gdx.utils.Array;
import enemies.Enemy;
import utilities.Node;
import utilities.Radar;

public class SingleProjectile extends Projectile {

    public SingleProjectile(int health, float damage, float speed, float radius, Node direction, Node position) {
        super(health, damage, speed, radius, direction, position);
    }

    public void damage(Radar radar, Array<Enemy> allEnemies) {
        Enemy enemy = radar.scan(this.getPosition(), this.getRadius(), allEnemies).first();
        enemy.hurt(this.getDamage());
        this.reduceHealth();
    }
}
