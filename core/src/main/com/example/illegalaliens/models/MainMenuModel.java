package com.example.illegalaliens.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.screens.IllegalAliensMain;
import com.example.illegalaliens.utilities.path.map.Map;


public class MainMenuModel {
	private IllegalAliensMain game;
	private Map map;
	
	public MainMenuModel(IllegalAliensMain game){
		this.game = game;
		this.map = new Map("map1", new Texture("maps/map1.png"));
	}
	
	public void setMap(String id){
		map = new Map(id, new Texture("maps/"+ id + ".png"));
	}
	
	public void showMainMenuStage(){
		notifyObservers("MainMenu");
	}
	
	public void showAboutStage(){
		notifyObservers("About");
	}
	
	public void showSelectMapStage(){
		notifyObservers("SelectMap");
	}
	
	public void switchToMainMenuScreen(){
		game.switchToMainMenuScreen();
	}
	
	public void startGame(){
		game.startGame(map);
	}
	
	
	
	
	
	
	private Array<MainMenuObserver> observers = new Array<MainMenuObserver>(false, 10);

	public void addObserver(MainMenuObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(MainMenuObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers(String id) {
		for (MainMenuObserver observer : observers)
			observer.actOnMainMenuChange(id);
	}
}
