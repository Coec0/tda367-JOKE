package projectiles;

import com.badlogic.gdx.graphics.Texture;
import utilities.Node;

import java.awt.*;

/**
 * Created by Emil on 2017-04-04.
 */
public class Missile extends Projectile {

    private static final float DAMAGE = 40;
    private static final float SPEED = 10;
    private static final boolean AREAOFEFFECT = true;

    public Missile(Node DIRECTION, Node POSITION){
        super(DAMAGE, SPEED, AREAOFEFFECT, DIRECTION, POSITION);
    }

}
