package controllers;

import com.badlogic.gdx.InputAdapter;

import models.AlienModel;
import utilities.UpdateObserver;
import views.AlienView;

public class AlienController extends InputAdapter implements UpdateObserver {

	AlienView AView;
	AlienModel AModel;

	public AlienController() {
		AView = new AlienView();
		AModel = new AlienModel();

	}

	// JUST FOR TESTING
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		AModel.createAlien();
		AView.addToView(AModel.peekAlien().getSpriteAdapter());
		return super.touchDown(screenX, screenY, pointer, button);
	}

	@Override
	public void update(float deltaTime) {
		AModel.moveAllAliens();
	}

}
