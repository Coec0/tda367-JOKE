package views;

import com.badlogic.gdx.graphics.Texture;

import projectiles.ArtilleryRound;
import projectiles.BazookaMissile;
import projectiles.Bullet;
import projectiles.EngineerBullet;
import projectiles.Missile;
import projectiles.Net;
import projectiles.Projectile;
import textures.ProjectileTextureHandler;
import utilities.DrawablesCollector;
import utilities.SpriteAdapter;

/**
 * Created by Emil on 2017-04-25.
 */
public class ProjectileView extends View<Projectile> {
    private DrawablesCollector SC = DrawablesCollector.getInstance();

    @Override
    protected Texture selectTexture(Projectile projectile) {

        if(projectile instanceof Bullet)
            return ProjectileTextureHandler.getBulletTexture();
        if(projectile instanceof Missile)
            return ProjectileTextureHandler.getMissileTexture();
        if(projectile instanceof ArtilleryRound)
            return ProjectileTextureHandler.getArtilleryRoundTexture();
        if(projectile instanceof EngineerBullet)
            return ProjectileTextureHandler.getEngineerBulletTexture();
        if (projectile instanceof BazookaMissile)
            return ProjectileTextureHandler.getBazookaMissileTexture();
        if(projectile instanceof Net){
        	return ProjectileTextureHandler.getNetTexture();
        }
        return null;
    }

    public void removeFromView(SpriteAdapter sprite){
        SC.removeSprite(sprite);
    }
}
