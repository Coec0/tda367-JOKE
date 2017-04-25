package views;

import utilities.SpriteCollector;
import utilities.TextArea;

public class HUDView {
	private SpriteCollector SC = SpriteCollector.getInstance();
	
	public HUDView(){
		
	}
	
	public void addToView(TextArea text) {
		SC.addText(text);
	}
}
