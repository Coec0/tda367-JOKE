package projectiles;

import com.badlogic.gdx.graphics.Texture;
import utilities.Node;


/**
 * Created by Emil on 2017-04-04.
 */
public class Bullet extends Projectile {

    private static final boolean AREAOFEFFECT = false;
    private static final float RADIUS = 20; 

    public Bullet(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED){
        super(DAMAGE, SPEED, AREAOFEFFECT, RADIUS, DIRECTION, POSITION);
    }
}
