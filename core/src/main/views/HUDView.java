package views;

import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import stages.HUDStage;
import utilities.SpriteCollector;
import utilities.TextArea;

public class HUDView {
	private SpriteCollector SC = SpriteCollector.getInstance();
	public HUDView(ClickListener CL, InputMultiplexer imp){
		
		HUDStage HS = new HUDStage(CL); 
		imp.addProcessor(HS);
		addToView(HS);
	}
	
	public void addToView(TextArea text) {
		SC.addText(text);
	}
	
	public void addToView(Stage stage) {
		SC.addStage(stage);
	}
	
	
}
