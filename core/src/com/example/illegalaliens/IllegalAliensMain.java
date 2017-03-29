package com.example.illegalaliens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

import screens.GameScreen;

public class IllegalAliensMain extends Game {
	
	
	@Override
	public void create () {
		Screen GameScreen = new GameScreen();
		setScreen(GameScreen);
	}

	@Override
	public void render () {
		super.render();
	}
}
