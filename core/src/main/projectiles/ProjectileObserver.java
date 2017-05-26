package projectiles;

/**
 * Created by Emil on 2017-04-28.
 */
public interface ProjectileObserver {
    public void actOnProjectileChange(Projectile projectile, String change);
}
