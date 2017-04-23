package views;

import com.badlogic.gdx.graphics.Texture;

import enemies.Alien;
import enemies.AlienWithHelmet;
import enemies.Enemy;
import enemies.SneakyAlien;
import utilities.AlienObserver;
import utilities.SpriteAdapter;
import utilities.SpriteCollector;

public class AlienView implements AlienObserver {
	private Texture alien, alienWithHelmet, sneakyAlien;
	private SpriteCollector SC = SpriteCollector.getInstance();
	
	public AlienView(){
		alien = new Texture("alien.png");
		alienWithHelmet = new Texture("alienwithhelmet.png");
		sneakyAlien = new Texture("sneakyalien.png");
	}
	
	public void removeFromView(SpriteAdapter sprite){
		SC.removeSprite(sprite);	
	}
	
	public void addToView(SpriteAdapter sprite, int x, int y){
		
		sprite.setPosition(x, y);
		addToView(sprite);
	}
	
	public void addToView(SpriteAdapter sprite){
		SC.addSprite(sprite);
	}
	
	public void addToView(Enemy enemy){
    	SpriteAdapter sprite = enemy.getSpriteAdapter();
        if(sprite.getTexture() == null){
            sprite.setTexture(selectTexture(enemy));
            sprite.setSize(sprite.getWidth()/2, sprite.getHeight()/2);
        }
        addToView(sprite);
    }

	private Texture selectTexture(Enemy enemy) {
		
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
			addToView(enemy);
		else
			removeFromView(enemy.getSpriteAdapter());
		
	}
	
}
