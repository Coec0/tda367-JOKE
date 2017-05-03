package projectiles;

import com.badlogic.gdx.graphics.Texture;
import utilities.Node;

/**
 * Created by Emil on 2017-04-17.
 */
public class ArtilleryRound extends Projectile {

    private static final boolean AREAOFEFFECT = true;
    private static final float RADIUS = 20; 

    public ArtilleryRound(Node DIRECTION, Node POSITION, float DAMAGE, float SPEED){
        super(DAMAGE, SPEED, AREAOFEFFECT, RADIUS, DIRECTION, POSITION);
    }
}
