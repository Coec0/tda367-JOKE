package com.example.illegalaliens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

import controllers.AlienController;
import screens.GameScreen;

public class IllegalAliensMain extends Game {
	
	
	@Override
	public void create () {
		//JUST FOR TESTING
		Gdx.input.setInputProcessor(new AlienController());
		
		Screen GameScreen = new GameScreen();
		setScreen(GameScreen);
	}

	@Override
	public void render () {
		super.render();
	}
}
