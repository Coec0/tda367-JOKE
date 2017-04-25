package utilities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

public final class SpriteCollector {
	private static final SpriteCollector instance = new SpriteCollector();
	private Array<SpriteAdapter> sprites;
	private Array<TextArea> textAreas;
	private BitmapFont font;
	
	private SpriteCollector(){
		sprites = new Array<SpriteAdapter>(false, 10);
		textAreas = new Array<TextArea>(false, 10);
		font = new BitmapFont();
	}
	
	public static SpriteCollector getInstance(){
		return instance;
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
	
	/**
	 * Draw all sprites. Must be called from 'render()' method
	 * @param batch
	 */
	public void drawSprites(SpriteBatch batch) {
		if(getSprites() != null){
			for(SpriteAdapter sprite : getSprites()){
				sprite.draw(batch);
			}
		}
	}
	
	/**
	 * Get all the texts that should be drawn
	 * @return
	 */
	public Array<TextArea> getTexts(){
		return textAreas;
	}
	
	/**
	 * Add text that should be drawn by the screen
	 * @param text
	 */
	public void addText(TextArea text){
		textAreas.add(text);
	}
	
	/**
	 * Remove a specific text
	 * @param text
	 */
	public void removeText(TextArea text){
		textAreas.removeValue(text, false);
	}
	
	/**
	 * Draw all texts. Must be called from 'render()' method
	 * @param batch
	 */
	public void drawText(SpriteBatch batch) {
		if(getTexts() != null){
			for(TextArea text : getTexts()){
				font.setColor(text.getColor());
				font.draw(batch, text.getText(),text.getX(), text.getY());
			}
		}
	}
	/**
	 * Draws everything. Must be called from 'render()' method
	 * @param batch
	 */
	public void drawAll(SpriteBatch batch){
		drawText(batch);
		drawSprites(batch);
	}
}
