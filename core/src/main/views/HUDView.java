package views;

import com.badlogic.gdx.scenes.scene2d.Stage;

import stages.HUDStage;
import utilities.SpriteCollector;
import utilities.TextArea;

public class HUDView {
	HUDStage HS;
	private SpriteCollector SC = SpriteCollector.getInstance();
	
	public HUDView(){

	}
	
	public void addToView(TextArea text) {
		SC.addText(text);
	}
	
	public void addToView(Stage stage) {
		SC.addStage(stage);
	}
	
	
}
