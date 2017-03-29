package controllers;

import com.badlogic.gdx.InputAdapter;

import utilities.SpriteAdapter;
import views.AlienView;

public class AlienController extends InputAdapter{
	AlienView AView;
	public AlienController(){
		AView = new AlienView();
	}
	
	
	//JUST FOR TESTING
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		AView.addToView(new SpriteAdapter(screenX, screenY));
		return super.touchDown(screenX, screenY, pointer, button);
	}

}
