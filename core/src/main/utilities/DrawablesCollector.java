package utilities;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;

public final class DrawablesCollector {
	private static final DrawablesCollector instance = new DrawablesCollector();
	private Array<SpriteAdapter> sprites;
	private Array<Stage> stages;
	
	private DrawablesCollector(){
		sprites = new Array<SpriteAdapter>(false, 10);
		stages = new Array<Stage>(false, 10);
	}
	
	public static DrawablesCollector getInstance(){
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
				stage.getViewport().apply();
				stage.draw();
			}
		}
	}
	
	/**
	 * Draws everything except stages. Must be called inside SpriteBatch
	 * @param batch
	 */
	public void drawAll(SpriteBatch batch){
		drawSprites(batch);
	}
}
