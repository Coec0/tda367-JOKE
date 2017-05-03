package views;

import buildings.towers.Bazooka;
import com.badlogic.gdx.graphics.Texture;
import projectiles.*;
import utilities.SpriteAdapter;
import utilities.DrawablesCollector;

/**
 * Created by Emil on 2017-04-25.
 */
public class ProjectileView {
    private Texture bullet, missile, artilleryRound, engineerBullet, bazookaMissile;
    private DrawablesCollector SC = DrawablesCollector.getInstance();

    public ProjectileView(){
        bullet = new Texture("bullet.png");
        missile = new Texture("missile.png");
        artilleryRound = new Texture("bullet.png");
        engineerBullet = new Texture("bullet.png");
        bazookaMissile = new Texture("bullet.png");
    }

    public void addToView(SpriteAdapter sprite){
        SC.addSprite(sprite);
    }

    public void addToView(Projectile projectile){
        SpriteAdapter sprite = projectile.getSpriteAdapter();
        if(sprite.getTexture() == null){
            sprite.setTexture(selectTexture(projectile));
            sprite.setSize(sprite.getWidth()/4, sprite.getHeight()/4);
        }
        addToView(sprite);
    }

    private Texture selectTexture(Projectile projectile) {

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
