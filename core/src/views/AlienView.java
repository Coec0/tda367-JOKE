package views;

import com.badlogic.gdx.graphics.Texture;

import utilities.SpriteAdapter;
import utilities.SpriteCollector;

public class AlienView {
	private Texture texture;
	private SpriteCollector SC = SpriteCollector.getInstance();
	
	public AlienView(){
		texture = new Texture("alien.png");
	}
	
	public void removeFromView(SpriteAdapter sprite){
		SC.removeSprite(sprite);	
	}
	
	public void addToView(SpriteAdapter sprite, int x, int y){
		sprite.setSize(sprite.getHeight()/4, sprite.getWidth()/4);
		sprite.setPosition(x, y);
		addToView(sprite);
	}
	
	public void addToView(SpriteAdapter sprite){
		if(sprite.getTexture() == null)
			sprite.setTexture(texture);
		SC.addSprite(sprite);
	}
	
}
