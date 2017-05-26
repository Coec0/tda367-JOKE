package com.example.illegalaliens.models.projectiles;

import com.example.illegalaliens.utilities.Node;

public class ProjectileFactory {

    public static Projectile createBullet(Node direction, Node position, float damage, float speed) {
        return new Bullet(direction, position, damage, speed);
    }

    public static Projectile createMissile(Node direction, Node position, float damage, float speed) {
        return new Missile(direction, position, damage, speed);
    }
}
