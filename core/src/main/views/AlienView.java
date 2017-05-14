package views;

import com.badlogic.gdx.graphics.Texture;

import enemies.Alien;
import enemies.AlienWithHelmet;
import enemies.Enemy;
import enemies.SneakyAlien;
import observers.AlienObserver;

public class AlienView extends View<Enemy> implements AlienObserver {
	private Texture alien, alienWithHelmet, sneakyAlien;
	
	public AlienView(){
		alien = new Texture("alien.png");
		alienWithHelmet = new Texture("alienwithhelmet.png");
		sneakyAlien = new Texture("sneakyalien.png");
	}
	
	@Override
	protected Texture selectTexture(Enemy enemy) {
		
		if(enemy instanceof Alien)
			return alien;
		if(enemy instanceof SneakyAlien)
			return sneakyAlien;
		if(enemy instanceof AlienWithHelmet)
			return alienWithHelmet;
		return null;
		
	}

	@Override
	public void actOnEnemyChange(Enemy enemy, boolean remove) {
		if(!remove)
			addToView(enemy.getSpriteAdapter(), enemy, 0.3f);
		else
			removeFromView(enemy.getSpriteAdapter());
		
	}
	
}
