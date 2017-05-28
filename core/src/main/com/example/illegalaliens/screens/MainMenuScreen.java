package com.example.illegalaliens.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.illegalaliens.controllers.MainMenuController;
import com.example.illegalaliens.models.MainMenuModel;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.views.MainMenuView;
import com.example.illegalaliens.views.stages.AboutStage;
import com.example.illegalaliens.views.stages.HiscoreStage;
import com.example.illegalaliens.views.stages.MainMenuStage;
import com.example.illegalaliens.views.stages.MapSelectStage;

public class MainMenuScreen implements Screen {

	private DrawablesCollector DC;
	private MainMenuController mainMenuController;
	private MainMenuModel model;
	private final int width = 1280;
	private Viewport WP;
	private Camera camera;
	public MainMenuScreen(IllegalAliensMain game, Viewport WP, Camera camera){
		this.WP = WP;
		this.camera = camera;
		DC = new DrawablesCollector();
		model = new MainMenuModel(game);
		mainMenuController = new MainMenuController(model);
		MainMenuStage mainMenuStage = new MainMenuStage(mainMenuController);
		AboutStage aboutStage = new AboutStage(mainMenuController);
		MapSelectStage mapSelectStage = new MapSelectStage(mainMenuController);
		HiscoreStage hiscoreStage = new HiscoreStage(mainMenuController, game.getScores());
		
		MainMenuView mainMenuView = new MainMenuView(DC, mainMenuStage, aboutStage, mapSelectStage, hiscoreStage);
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
		camera.update();
		WP.apply();
		DC.drawStages();
	}

	@Override
	public void resize(int width, int height) {
		WP.update(width-200*width/this.width, height, true);
		DC.refreshStagesVP();
		
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
