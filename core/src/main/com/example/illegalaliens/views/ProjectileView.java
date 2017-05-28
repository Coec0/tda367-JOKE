package com.example.illegalaliens.views;

import com.badlogic.gdx.graphics.Texture;
import com.example.illegalaliens.models.projectiles.Bullet;
import com.example.illegalaliens.models.projectiles.Missile;
import com.example.illegalaliens.models.projectiles.Net;
import com.example.illegalaliens.models.projectiles.Projectile;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.utilities.IAAdapter;
import com.example.illegalaliens.views.textures.ProjectileTextureHandler;

/**
 * Created by Emil on 2017-04-25.
 */
public class ProjectileView extends View<Projectile> {

    public ProjectileView(DrawablesCollector DC) {
		super(DC);
	}

	@Override
    protected Texture selectTexture(Projectile projectile) {

        if(projectile instanceof Bullet)
            return ProjectileTextureHandler.getBulletTexture();
        if(projectile instanceof Missile)
            return ProjectileTextureHandler.getMissileTexture();
        if(projectile instanceof Net){
        	return ProjectileTextureHandler.getNetTexture();
        }
        return null;
    }

    public void removeFromView(IAAdapter sprite){
        super.getDrawablesCollector().removeSprite(sprite);
    }
}
