package projectiles;

import com.badlogic.gdx.graphics.Texture;
import utilities.Node;


/**
 * Created by Emil on 2017-04-04.
 */
public class Bullet extends Projectile {

    private static final float DAMAGE = 10;
    private static final float SPEED = 10;
    private static final boolean AREAOFEFFECT = false;

    public Bullet(Node DIRECTION, Node POSITION){
        super(DAMAGE, SPEED, AREAOFEFFECT, DIRECTION, POSITION);
    }
}
