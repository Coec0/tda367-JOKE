package views;

import com.badlogic.gdx.graphics.Texture;

import enemies.Alien;
import enemies.AlienWithHelmet;
import enemies.Enemy;
import enemies.SneakyAlien;
import observers.AlienObserver;

public class AlienView extends View<Enemy> implements AlienObserver {
	private Texture alien, alienWithHelmet, sneakyAlien,toughalien,highalien;
	
	public AlienView(){
		alien = new Texture("alien512.png");
		alienWithHelmet = new Texture("alienwithhelmet512.png");
		sneakyAlien = new Texture("sneakyalien512.png");
		toughalien = new Texture("toughalien512.png");
		highalien = new Texture("highalien512.png");
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
