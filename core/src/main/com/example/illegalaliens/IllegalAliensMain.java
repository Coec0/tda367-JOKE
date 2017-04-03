package com.example.illegalaliens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;

import screens.MainMenuScreen;
import utilities.UpdateObserver;


public class IllegalAliensMain extends Game {
	SpriteBatch batch;
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		//GameScreen = new GameScreen(this, batch);
		setScreen(new MainMenuScreen(this,batch));
		//setScreen(GameScreen);
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
