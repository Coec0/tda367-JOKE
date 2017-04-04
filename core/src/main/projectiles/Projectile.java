package projectiles;

/**
 * Created by Emil on 2017-04-04.
 */
public abstract class Projectile {

    private float radius;
    private float damage;
    private float speed;
    private boolean areaOfEffect;


    public Projectile(float radius, float damage, float speed, boolean areaOfEffect){
        this.radius = radius;
        this.damage = damage;
        this.speed = speed;
        this.areaOfEffect = areaOfEffect;
    }



}
