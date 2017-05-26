package com.example.illegalaliens.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.illegalaliens.controllers.MainMenuController;
import com.example.illegalaliens.models.MainMenuModel;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.views.MainMenuView;
import com.example.illegalaliens.views.stages.AboutStage;
import com.example.illegalaliens.views.stages.MainMenuStage;
import com.example.illegalaliens.views.stages.MapSelectStage;

public class MainMenuScreen implements Screen {

	private SpriteBatch batch;
	private DrawablesCollector DC;
	private MainMenuController mainMenuController;
	private MainMenuModel model;
	public MainMenuScreen(IllegalAliensMain game, SpriteBatch batch){

		this.batch = batch;
		DC = new DrawablesCollector();
		model = new MainMenuModel(game);
		mainMenuController = new MainMenuController(model);
		MainMenuStage mainMenuStage = new MainMenuStage(mainMenuController);
		AboutStage aboutStage = new AboutStage(mainMenuController);
		MapSelectStage mapSelectStage = new MapSelectStage(mainMenuController);
		
		MainMenuView mainMenuView = new MainMenuView(DC, mainMenuStage, aboutStage, mapSelectStage);
		model.addObserver(mainMenuView);
	}

	public MainMenuController getMainMenuController(){
		return mainMenuController;
	}
	
	@Override
	public void show() {
		model.showMainMenuStage();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		batch.begin();
		DC.drawStages();
		batch.end();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {

	}
	

}
