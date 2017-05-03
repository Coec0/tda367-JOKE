package projectiles;

import utilities.Node;

/**
 * Created by Emil on 2017-05-02.
 */
public class BazookaMissile extends Projectile {
    private static final boolean AREAOFEFFECT = true;
    private static final float RADIUS = 20;

    public BazookaMissile(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED){
        super(DAMAGE, SPEED, AREAOFEFFECT, RADIUS, DIRECTION, POSITION);
    }
}
