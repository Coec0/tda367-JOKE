package utilities;

import com.badlogic.gdx.utils.Array;

public class SpriteCollector {
	static SpriteCollector SC;
	private Array<SpriteAdapter> sprites;
	
	private SpriteCollector(){
		sprites = new Array<SpriteAdapter>(false, 10);
	}
	
	public SpriteCollector getInstace(){
		if (SC == null)
			SC = new SpriteCollector();
		return SC;
	}
	
	/**
	 * Get all the sprites that should be drawn
	 * @return
	 */
	public Array<SpriteAdapter> getSprites(){
		return sprites;
	}
	
	/**
	 * Add sprite that should be drawn by the screen
	 * @param sprite
	 */
	public void addSprite(SpriteAdapter sprite){
		sprites.add(sprite);
	}
	
	/**
	 * Remove a specific sprite
	 * @param sprite
	 */
	public void removeSprite(SpriteAdapter sprite){
		sprites.removeValue(sprite, false);
	}	
}
