package com.example.illegalaliens.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.example.illegalaliens.screens.IllegalAliensMain;
import com.example.illegalaliens.utilities.path.map.Map;


public class MainMenuModel {
	private IllegalAliensMain game;
	private Map map;
	
	public MainMenuModel(IllegalAliensMain game){
		this.game = game;
		this.map = new Map("AlphaMap", new Texture("maps/AlphaMap.png"));
	}
	
	public void setMap(String id){
		map = new Map(id, new Texture("maps/"+ id + ".png"));
		notifyObservers(id,null);
	}
	
	public void showMainMenuStage(){
		notifyObservers("MainMenu", null);
	}
	
	public void showAboutStage(){
		notifyObservers("About", null);
	}
	
	public void showSelectMapStage(){
		notifyObservers("SelectMap", null);
		notifyObservers("AlphaMap",null);
	}

	public void showHiscoreStage() {
		notifyObservers("Hiscore", game.getScores());
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

	private void notifyObservers(String id, IntArray scores) {
		for (MainMenuObserver observer : observers)
			observer.actOnMainMenuChange(id, scores);
	}
}
