package com.example.illegalaliens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.utils.Array;

import controllers.AlienController;
import screens.GameScreen;
import utilities.UpdateObserver;

public class IllegalAliensMain extends Game {

	AlienController AController;
	@Override
	public void create() {
		
		AController = new AlienController();
		
		// JUST FOR TESTING
		Gdx.input.setInputProcessor(AController);
		this.addObserver(AController);
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
