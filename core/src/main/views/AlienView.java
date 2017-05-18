package views;

import com.badlogic.gdx.graphics.Texture;

import enemies.Alien;
import enemies.AlienWithHelmet;
import enemies.Enemy;
import enemies.HighAlien;
import enemies.SneakyAlien;
import enemies.ToughAlien;
import observers.AlienObserver;

public class AlienView extends View<Enemy> implements AlienObserver {
	private Texture alien, alienWithHelmet, sneakyAlien,toughalien,highalien;
	
	public AlienView(){
		alien = new Texture("aliens/alien/alien512.png");
		alienWithHelmet = new Texture("aliens/alienwithhelmet/alienwithhelmet512.png");
		sneakyAlien = new Texture("aliens/sneakyalien/sneakyalien512.png");
		toughalien = new Texture("aliens/toughalien/toughalien512.png");
		highalien = new Texture("aliens/highalien/highalien512.png");
	}
	
	@Override
	protected Texture selectTexture(Enemy enemy) {
		
		if(enemy instanceof Alien)
			return alien;
		if(enemy instanceof SneakyAlien)
			return sneakyAlien;
		if(enemy instanceof AlienWithHelmet)
			return alienWithHelmet;
		if(enemy instanceof HighAlien)
			return highalien;
		if(enemy instanceof ToughAlien)
			return toughalien;
		
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
