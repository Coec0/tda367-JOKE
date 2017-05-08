package views;

import com.badlogic.gdx.graphics.Texture;

import projectiles.ArtilleryRound;
import projectiles.BazookaMissile;
import projectiles.Bullet;
import projectiles.EngineerBullet;
import projectiles.Missile;
import projectiles.Projectile;
import utilities.DrawablesCollector;
import utilities.SpriteAdapter;

/**
 * Created by Emil on 2017-04-25.
 */
public class ProjectileView extends View<Projectile> {
    private Texture bullet, missile, artilleryRound, engineerBullet, bazookaMissile;
    private DrawablesCollector SC = DrawablesCollector.getInstance();

    public ProjectileView(){
        bullet = new Texture("bullet.png");
        missile = new Texture("missile.png");
        artilleryRound = new Texture("bullet.png");
        engineerBullet = new Texture("bullet.png");
        bazookaMissile = new Texture("bullet.png");
    }



    @Override
    protected Texture selectTexture(Projectile projectile) {

        if(projectile instanceof Bullet)
            return bullet;
        if(projectile instanceof Missile)
            return missile;
        if(projectile instanceof ArtilleryRound)
            return artilleryRound;
        if(projectile instanceof EngineerBullet)
            return engineerBullet;
        if (projectile instanceof BazookaMissile)
            return bazookaMissile;
        return null;
    }

    public void removeFromView(SpriteAdapter sprite){
        SC.removeSprite(sprite);
    }
}
