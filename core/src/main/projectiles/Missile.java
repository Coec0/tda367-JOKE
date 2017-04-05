package projectiles;

import com.badlogic.gdx.graphics.Texture;
import utilities.Node;

import java.awt.*;

/**
 * Created by Emil on 2017-04-04.
 */
public class Missile extends Projectile {

    private static final float RADIUS = 20;
    private static final float DAMAGE = 40;
    private static final float SPEED = 10;
    private static final boolean AREAOFEFFECT = true;
    private static final Texture TEXTURE = new Texture("missile.png");

    public Missile(){
        super(RADIUS, DAMAGE, SPEED, AREAOFEFFECT, TEXTURE);
    }

}
