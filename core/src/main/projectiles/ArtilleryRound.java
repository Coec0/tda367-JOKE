package projectiles;

import com.badlogic.gdx.graphics.Texture;
import utilities.Node;

/**
 * Created by Emil on 2017-04-17.
 */
public class ArtilleryRound extends AOEProjectile {

    private static final float AREAOFEFFECTRADIUS = 30;
    private static final float RADIUS = 20;
    private static final int HEALTH = 1;

    public ArtilleryRound(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED){
        super(HEALTH, DAMAGE, SPEED, RADIUS, AREAOFEFFECTRADIUS, DIRECTION, POSITION);
    }
}
