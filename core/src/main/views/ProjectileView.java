package views;

import com.badlogic.gdx.graphics.Texture;
import projectiles.*;
import utilities.SpriteAdapter;
import utilities.SpriteCollector;

/**
 * Created by Emil on 2017-04-25.
 */
public class ProjectileView {
    private Texture bullet, missile, rangerBullet, sniperBullet, artilleryRound;
    private SpriteCollector SC = SpriteCollector.getInstance();

    public ProjectileView(){
        bullet = new Texture("bullet.png");
        missile = new Texture("bullet.png");
        rangerBullet = new Texture("bullet.png");
        sniperBullet = new Texture("bullet.png");
        artilleryRound = new Texture("bullet.png");
    }

    public void addToView(SpriteAdapter sprite){
        SC.addSprite(sprite);
    }

    private Texture selectTexture(Projectile projectile) {

        if(projectile instanceof Bullet)
            return bullet;
        if(projectile instanceof Missile)
            return missile;
        if(projectile instanceof SniperBullet)
            return sniperBullet;
        if(projectile instanceof RangerBullet)
            return rangerBullet;
        if(projectile instanceof ArtilleryRound)
            return artilleryRound;
        return null;
    }

    public void removeFromView(SpriteAdapter sprite){
        SC.removeSprite(sprite);
    }
}
