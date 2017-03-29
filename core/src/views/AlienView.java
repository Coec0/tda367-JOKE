package views;

import com.badlogic.gdx.graphics.Texture;

import utilities.SpriteAdapter;
import utilities.SpriteCollector;

public class AlienView {
	private Texture texture;
	SpriteCollector SC;
	
	public AlienView(){
		SC = SpriteCollector.getInstace();
		texture = new Texture("badlogic.jpg");
	}
	
	public void removeFromView(SpriteAdapter sprite){
		SC.removeSprite(sprite);	
	}
	
	public void addToView(SpriteAdapter sprite, int x, int y){
		sprite.getSprite().setPosition(x, y);
		addToView(sprite);
	}
	
	public void addToView(SpriteAdapter sprite){
		//if(sprite.getSprite().getTexture() == null)
			sprite.setTexture(texture);
		SC.addSprite(sprite);
	}
	
}
