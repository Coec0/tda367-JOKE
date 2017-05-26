package com.example.illegalaliens.controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.models.MainMenuModel;

public class MainMenuController extends ClickListener {
	private MainMenuModel model;
	public MainMenuController(MainMenuModel model) {
		this.model = model;
		
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (event.getListenerActor().getName().equals("backToMainMenu")) {
			model.showMainMenuStage();
		} else if (event.getListenerActor().getName().equals("mapSelectStage")) {
			model.showSelectMapStage();
		} else if (event.getListenerActor().getName().equals("aboutStage")) {
			model.showAboutStage();
		} else if (event.getListenerActor().getName().equals("map1")){
			model.setMap(event.getListenerActor().getName());
		} else if (event.getListenerActor().getName().equals("map2")) {
			model.setMap(event.getListenerActor().getName());
		} else if (event.getListenerActor().getName().equals("startGame")) {
			System.out.println("STARTINGGAMEEE");
			model.startGame();
		} else if(event.getListenerActor().getName().equals("goToMainMenu")){
			model.switchToMainMenuScreen();
		} else if(event.getListenerActor().getName().equals("AlphaMap")){
			model.setMap(event.getListenerActor().getName());
		}
			
	}
}
