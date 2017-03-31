package com.example.illegalaliens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;

import controllers.AlienController;
import controllers.TowerController;
import models.AlienModel;
import models.TowerModel;
import screens.GameScreen;
import utilities.UpdateObserver;
import views.AlienView;
import views.TowerView;

public class IllegalAliensMain extends Game {

	AlienController AController;
	TowerController TController;
	@Override
	public void create() {
		//Maybe move to Screen later
		
		AlienView AW= new AlienView();
		AlienModel AM= new AlienModel();
		TowerModel TM = new TowerModel();
		TowerView TW = new TowerView();
		
		AController = new AlienController(AW, AM);
		TController = new TowerController(TM, AM, TW);
		
		// JUST FOR TESTING
		Gdx.input.setInputProcessor(AController);
		this.addObserver(AM);
		//this.addObserver(observer);
		Screen GameScreen = new GameScreen();
		setScreen(GameScreen);
	}

	private Array<UpdateObserver> observers = new Array<UpdateObserver>(false, 10);

	public void addObserver(UpdateObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(UpdateObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers() {
		for (UpdateObserver observer : observers)
			observer.update(Gdx.graphics.getDeltaTime());
	}

	@Override
	public void render() {
		notifyObservers();
		super.render();	
	}
}
