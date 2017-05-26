package com.example.illegalaliens.models.projectiles;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.Radar;

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
