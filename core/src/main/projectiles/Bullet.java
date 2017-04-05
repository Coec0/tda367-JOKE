package projectiles;

import com.badlogic.gdx.graphics.Texture;


/**
 * Created by Emil on 2017-04-04.
 */
public class Bullet extends Projectile {

    private static final float RADIUS = 5;
    private static final float DAMAGE = 10;
    private static final float SPEED = 20;
    private static final boolean AREAOFEFFECT = false;
    private static final Texture TEXTURE = new Texture("bullet.png");

    public Bullet(){
        super(RADIUS, DAMAGE, SPEED, AREAOFEFFECT, TEXTURE);
    }
}
