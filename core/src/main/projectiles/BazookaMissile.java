package projectiles;

import utilities.Node;

/**
 * Created by Emil on 2017-05-02.
 */
public class BazookaMissile extends Projectile {
    private static final float DAMAGE = 15;
    private static final float SPEED = 50;
    private static final boolean AREAOFEFFECT = true;
    private static final float RADIUS = 20;

    public BazookaMissile(Node DIRECTION, Node POSITION){
        super(DAMAGE, SPEED, AREAOFEFFECT, RADIUS, DIRECTION, POSITION);
    }
}
