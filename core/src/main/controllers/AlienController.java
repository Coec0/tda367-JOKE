package controllers;

import enemies.Enemy;
import models.AlienModel;
import views.AlienView;

public class AlienController{

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
}
