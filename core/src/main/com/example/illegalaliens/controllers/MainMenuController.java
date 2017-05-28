package com.example.illegalaliens.controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.models.MainMenuModel;

public class MainMenuController extends ClickListener {
	private MainMenuModel MMmodel;
	public MainMenuController(MainMenuModel MMmodel) {
		this.MMmodel = MMmodel;
		
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (event.getListenerActor().getName().equals("backToMainMenu")) {
			MMmodel.showMainMenuStage();
		} else if (event.getListenerActor().getName().equals("mapSelectStage")) {
			MMmodel.showSelectMapStage();
		} else if (event.getListenerActor().getName().equals("aboutStage")) {
			MMmodel.showAboutStage();
		} else if (event.getListenerActor().getName().equals("map1")){
			MMmodel.setMap(event.getListenerActor().getName());
		} else if (event.getListenerActor().getName().equals("map2")) {
			MMmodel.setMap(event.getListenerActor().getName());
		} else if (event.getListenerActor().getName().equals("startGame")) {
			MMmodel.startGame();
		} else if(event.getListenerActor().getName().equals("goToMainMenu")){
			MMmodel.switchToMainMenuScreen();
		} else if(event.getListenerActor().getName().equals("AlphaMap")){
			MMmodel.setMap(event.getListenerActor().getName());
		} else if (event.getListenerActor().getName().equals("hiscoreStage")) {
			MMmodel.showHiscoreStage();
		}
			
	}
}
