package views;

import com.badlogic.gdx.graphics.Texture;

import enemies.Alien;
import enemies.AlienWithHelmet;
import enemies.Enemy;
import enemies.HighAlien;
import enemies.SneakyAlien;
import enemies.ToughAlien;
import observers.AlienObserver;
import textures.AlienTextureHandler;
import utilities.DrawablesCollector;
import utilities.SpriteAdapter;

public class AlienView extends View<Enemy> implements AlienObserver {
	
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
		SpriteAdapter sprite = enemy.getSpriteAdapter();
		//System.out.println(scale);
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
