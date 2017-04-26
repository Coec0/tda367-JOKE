package ui;

import java.awt.event.ActionListener;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.utils.Array;

import utilities.TextArea;
import views.HUDView;

public class HUD {
	Array<UIButton> buttons = new Array<UIButton>(false, 10);
	InputMultiplexer IM;
	//HUDView HW = new HUDView();
	
	public HUD(InputMultiplexer IM){
		this.IM = IM;
		UIButton nextWave = new UIButton(Gdx.graphics.getWidth()-100,Gdx.graphics.getHeight()-70, 100, 70, "nextWave", "Send next wave");
		addButton(nextWave);
		//HW.addToView(new TextArea(nextWave.getX(),Gdx.graphics.getHeight()-nextWave.getY(), nextWave.getWidth(), nextWave.getHeight(), nextWave.getText()));
	}
	
	private void addButton(UIButton button){
		IM.addProcessor(button);
		buttons.add(button);
	}
	
	public void addHUDListener(ActionListener l){
		for(UIButton button: buttons){
			button.addActionListener(l);
		}
	}
}
