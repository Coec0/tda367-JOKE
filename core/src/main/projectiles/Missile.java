package projectiles;

import utilities.Node;

/**
 * Created by Emil on 2017-04-04.
 */
public class Missile extends AOEProjectile {

    private static final float AREAOFEFFECTRADIUS = 5000;
    private static final float RADIUS = 20;
    private static final int HEALTH = 1;

    public Missile(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED){
        super(HEALTH, DAMAGE, SPEED,RADIUS, AREAOFEFFECTRADIUS, DIRECTION, POSITION);
    }
}
