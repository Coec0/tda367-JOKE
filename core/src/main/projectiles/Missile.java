package projectiles;

import com.badlogic.gdx.graphics.Texture;
import utilities.Node;

import java.awt.*;

/**
 * Created by Emil on 2017-04-04.
 */
public class Missile extends Projectile implements IAOEProjectile{

    private static final float AREAOFEFFECTRADIUS = 5000;
    private static final float RADIUS = 20;
    private static final int HEALTH = 1;

    public Missile(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED){
        super(HEALTH, DAMAGE, SPEED,RADIUS, DIRECTION, POSITION);
    }

    @Override
    public float getAOERadius() {
        return AREAOFEFFECTRADIUS;
    }

    @Override
    public void setAOERadius() {

    }
}
