package projectiles;

import com.badlogic.gdx.graphics.Texture;
import utilities.Node;

import java.awt.*;

/**
 * Created by Emil on 2017-04-04.
 */
public class Missile extends Projectile {

    private static final boolean AREAOFEFFECT = true;
    private static final float RADIUS = 20; 

    public Missile(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED){
        super(DAMAGE, SPEED, AREAOFEFFECT,RADIUS, DIRECTION, POSITION);
    }

}
