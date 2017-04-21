package controllers;

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

	public void spawnAlien(){
		AModel.createAlien();
		//AView.addToView(AModel.peekAlien().getSpriteAdapter());
	}
}
