package controllers;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

import enemies.Enemy;
import models.AlienModel;
import views.AlienView;

public class AlienController extends InputAdapter{

	AlienView AView;
	AlienModel AModel;

	public AlienController(AlienView AView, AlienModel AModel) {
		this.AView = AView;
		this.AModel = AModel;
		AModel.addObserver(AView);
	}

	public void spawnAlien(Enemy enemy){
		AModel.addAlien(enemy);
	}
	
	@Override
	public boolean keyDown (int keycode) {
		if(keycode == Keys.SPACE){
			AModel.startNextWave();
			return true;
		}
		return false;
	}
}
