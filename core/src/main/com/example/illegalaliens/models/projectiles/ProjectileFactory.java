package com.example.illegalaliens.models.projectiles;

import com.example.illegalaliens.utilities.Node;

public class ProjectileFactory {

    public static Projectile createBullet(Node direction, Node position, float damage, float speed,int HEALTH) {
        return new Bullet(direction, position, damage, speed, HEALTH);
    }

    public static Projectile createMissile(Node direction, Node position, float damage, float speed,int HEALTH) {
        return new Missile(direction, position, damage, speed, HEALTH);
    }
}
