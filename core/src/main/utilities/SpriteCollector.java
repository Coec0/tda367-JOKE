package utilities;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public final class SpriteCollector {
	private static final SpriteCollector instance = new SpriteCollector();
	private Array<SpriteAdapter> sprites;
	private Array<TextArea> textAreas;
	private Array<Stage> stages;
	private BitmapFont font;
	
	private SpriteCollector(){
		sprites = new Array<SpriteAdapter>(false, 10);
		textAreas = new Array<TextArea>(false, 10);
		stages = new Array<Stage>(false, 10);
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
	 * Draw all sprites. Must be called inside SpriteBatch
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
	 * Draw all texts. Must be called inside SpriteBatch
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
	 * Add stage that should be drawn by the screen
	 * @param stage
	 */
	public void addStage(Stage stage){
		stages.add(stage);
		System.out.println(getStages().size);
	}
	
	/**
	 * Remove a specific stage
	 * @param stage
	 */
	public void removeStage(Stage stage){
		stages.removeValue(stage, false);
	}
	
	/**
	 * Get all the stages that should be drawn
	 * @return
	 */
	public Array<Stage> getStages(){
		return stages;
	}
	
	
	/**
	 * Draw all stages. Must NOT be called inside SpriteBatch
	 * @param batch
	 */
	public void drawStages() {
		if(getStages() != null){
			for(Stage stage : getStages()){
				stage.draw();
			}
		}
	}
	
	/**
	 * Draws everything except stages. Must be called inside SpriteBatch
	 * @param batch
	 */
	public void drawAll(SpriteBatch batch){
		drawText(batch);
		drawSprites(batch);
	}
}
