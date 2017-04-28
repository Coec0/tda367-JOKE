package views;

import com.badlogic.gdx.scenes.scene2d.Stage;

import stages.GameUIStage;
import utilities.DrawablesCollector;

public class GameUIView {
	GameUIStage HS;
	private DrawablesCollector SC = DrawablesCollector.getInstance();
	
	public GameUIView(){

	}
	
	public void addToView(Stage stage) {
		SC.addStage(stage);
	}
	
	
}
