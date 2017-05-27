package com.example.illegalaliens.models.projectiles;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.Radar;

public class SingleProjectile extends Projectile {

    public SingleProjectile(int health, float damage, float speed, float radius, Node direction, Node position) {
        super(health, damage, speed, radius, direction, position);
    }

    public void damage(Radar radar, Array<Enemy> allEnemies) {
        Enemy enemy = super.scanEnemies(radar,this.getPosition(), this.getRadius(), allEnemies).first();
        enemy.hurt(this.getDamage());
        this.reduceHealth();
    }
}
