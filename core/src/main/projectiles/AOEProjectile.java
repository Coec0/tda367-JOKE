package projectiles;

import com.badlogic.gdx.utils.Array;
import enemies.Enemy;
import utilities.Node;
import utilities.Radar;
import interfaces.IAOEProjectile;

public class AOEProjectile extends Projectile implements IAOEProjectile {

    private float areaOfEffect;

    public AOEProjectile(int health, float damage, float speed, float radius, float areaOfEffect, Node direction, Node position) {
        super(health, damage, speed, radius, direction, position);

        this.areaOfEffect = areaOfEffect;
    }

    public float getAOERadius() {
        return areaOfEffect;
    }

    public void setAOERadius(float areaOfEffect) {
        this.areaOfEffect = areaOfEffect;
    }

    public void damage(Radar radar, Array<Enemy> allEnemies) {
        Array<Enemy> enemies = radar.scan(this.getPosition(), this.getAOERadius(), allEnemies);
        for (Enemy enemy: enemies) {
            enemy.hurt(this.getDamage());
        }
        this.reduceHealth();
    }
}
