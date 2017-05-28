package com.example.illegalaliens.views;

import com.badlogic.gdx.graphics.Texture;
import com.example.illegalaliens.models.enemies.Alien;
import com.example.illegalaliens.models.enemies.AlienWithHelmet;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.models.enemies.EnemyObserver;
import com.example.illegalaliens.models.enemies.HighAlien;
import com.example.illegalaliens.models.enemies.SneakyAlien;
import com.example.illegalaliens.models.enemies.ToughAlien;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.utilities.IAAdapter;
import com.example.illegalaliens.views.textures.AlienTextureHandler;

public class AlienView extends View<Enemy> implements EnemyObserver {
	
	public AlienView(DrawablesCollector DC) {
		super(DC);
	}
	
	@Override
	protected Texture selectTexture(Enemy enemy) {
		if(enemy.isInNet()){
			if(enemy instanceof Alien)
				return AlienTextureHandler.getAlienNetTexture();
			if(enemy instanceof SneakyAlien)
				return AlienTextureHandler.getSneakyAlienNetTexture();
			if(enemy instanceof AlienWithHelmet)
				return AlienTextureHandler.getAlienWithHelmetNetTexture();
			if(enemy instanceof HighAlien)
				return AlienTextureHandler.getHighAlienNetTexture();
			if(enemy instanceof ToughAlien)
				return AlienTextureHandler.getToughAlienNetTexture();
		}
		if(enemy instanceof Alien)
			return AlienTextureHandler.getAlienTexture();
		if(enemy instanceof SneakyAlien)
			return AlienTextureHandler.getSneakyAlienTexture();
		if(enemy instanceof AlienWithHelmet)
			return AlienTextureHandler.getAlienWithHelmetTexture();
		if(enemy instanceof HighAlien)
			return AlienTextureHandler.getHighAlienTexture();
		if(enemy instanceof ToughAlien)
			return AlienTextureHandler.getToughAlienTexture();
		
		return null;
		
	}
	
	

	@Override
	public void actOnEnemyChange(Enemy enemy, boolean remove) {
		IAAdapter sprite = enemy.getSpriteAdapter();
		if(!remove){
			if(enemy.isInNet()){
				sprite.setTexture(selectTexture(enemy));
				sprite.setSize(sprite.getWidth() * super.getScale(sprite, enemy.getRadius()), sprite.getHeight()*super.getScale(sprite, enemy.getRadius()));
			}else{
				addToView(enemy.getSpriteAdapter(), enemy, enemy.getRadius());
			}
			
		}else
			removeFromView(enemy.getSpriteAdapter());
		
	}
	
}
