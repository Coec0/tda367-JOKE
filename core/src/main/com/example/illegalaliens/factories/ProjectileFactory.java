package com.example.illegalaliens.factories;

import com.example.illegalaliens.models.projectiles.Bullet;
import com.example.illegalaliens.models.projectiles.Missile;
import com.example.illegalaliens.models.projectiles.Projectile;
import com.example.illegalaliens.utilities.Node;

public class ProjectileFactory {

    public static Projectile createBullet(Node direction, Node position, float damage, float speed) {
        return new Bullet(direction, position, damage, speed);
    }

    public static Projectile createMissile(Node direction, Node position, float damage, float speed) {
        return new Missile(direction, position, damage, speed);
    }
}
