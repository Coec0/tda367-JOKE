package projectiles;

import com.badlogic.gdx.graphics.Texture;
import utilities.Node;

/**
 * Created by Emil on 2017-04-17.
 */
public class SniperBullet extends Projectile {

    private static final float DAMAGE = 40;
    private static final float SPEED = 100;
    private static final boolean AREAOFEFFECT = false;

    public SniperBullet(Node DIRECTION, Node POSITION){
        super(DAMAGE, SPEED, AREAOFEFFECT, DIRECTION, POSITION);
    }

}
