package controllers;

import com.badlogic.gdx.InputAdapter;
import enemies.Alien;
import models.AlienModel;
import utilities.EnemyWavesCreator;
import views.AlienView;

public class AlienController{

	AlienView AView;
	AlienModel AModel;

	public AlienController(AlienView AView, AlienModel AModel) {
		this.AView = AView;
		this.AModel = AModel;
	}

	public void spawnAlien(){
		AModel.createAlien();
		AView.addToView(AModel.peekAlien().getSpriteAdapter());
	}
	
	
	
	
	/*// JUST FOR TESTING
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		/*AModel.createAlien();
		AView.addToView(AModel.peekAlien().getSpriteAdapter());
		return super.touchDown(screenX, screenY, pointer, button); 
		return super.touchDown(screenX, screenY, pointer, button);
	}*/
}
