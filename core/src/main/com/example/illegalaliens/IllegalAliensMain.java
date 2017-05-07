package com.example.illegalaliens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import observers.UpdateObserver;
import screens.EndGameScreen;
import screens.GameScreen;
import screens.MainMenuScreen;


public class IllegalAliensMain extends Game {

	private SpriteBatch batch;
	private GameScreen gameScreen;
	private EndGameScreen endGameScreen;
	private MainMenuScreen mainMenuScreen;
	private Array<UpdateObserver> observers;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		observers = new Array<UpdateObserver>(false, 10);

		mainMenuScreen = new MainMenuScreen(this, batch);
		gameScreen = new GameScreen(this, batch);
		endGameScreen = new EndGameScreen(this, batch);

		setScreen(mainMenuScreen);
	}

	@Override
	public void render() {
		notifyObservers();
		super.render();
	}

	public void setGameScreen() {
		setScreen(gameScreen);
	}

	public void setEndGameScreen() {
		setScreen(endGameScreen);
	}

	public void setMainMenuScreen() {
		setScreen(mainMenuScreen);
	}

	public void shutdown() {
		Gdx.app.exit();
	}

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
}
