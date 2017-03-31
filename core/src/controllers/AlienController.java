package controllers;

import com.badlogic.gdx.InputAdapter;

import models.AlienModel;
import views.AlienView;

public class AlienController extends InputAdapter{

	AlienView AView;
	AlienModel AModel;

	public AlienController(AlienView AView, AlienModel AModel) {
		this.AView = AView;
		this.AModel = AModel;

	}

	// JUST FOR TESTING
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		AModel.createAlien();
		AView.addToView(AModel.peekAlien().getSpriteAdapter());
		return super.touchDown(screenX, screenY, pointer, button);
	}
}
