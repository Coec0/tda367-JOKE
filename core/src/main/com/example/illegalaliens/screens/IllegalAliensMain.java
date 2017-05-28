package com.example.illegalaliens.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.IntArray;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.illegalaliens.UpdateObserver;
import com.example.illegalaliens.hiscore.DatabaseResolver;
import com.example.illegalaliens.hiscore.HiscoreDB;
import com.example.illegalaliens.utilities.path.map.Map;


public class IllegalAliensMain extends Game {

	private Array<UpdateObserver> observers;
	private SpriteBatch batch;
	private MainMenuScreen mainMenuScreen;
	private DatabaseResolver databaseResolver;
	private Viewport VP;
	private Camera camera;
	private final int width = 1280;
	private final int height = 720;
	private HiscoreDB hiscoreDB;

	public IllegalAliensMain(DatabaseResolver databaseResolver) {
		this.databaseResolver = databaseResolver;
	}
	
	@Override
	public void create() {

		camera = new OrthographicCamera();
		VP = new StretchViewport(width, height, camera);

		this.batch = new SpriteBatch();
		hiscoreDB = new HiscoreDB(databaseResolver);
		hiscoreDB.create();

		mainMenuScreen = new MainMenuScreen(this, batch, VP, camera);

		observers = new Array<UpdateObserver>(false, 10);


//		gameScreen = new GameScreen(this, batch);

		this.switchToMainMenuScreen();
	}

	@Override
	public void render() {
		notifyObservers();
		super.render();
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

    public void startGame(Map map) {
        setScreen(new GameScreen(this, map, batch, mainMenuScreen.getMainMenuController(), VP, camera));
    }

    public void switchToMainMenuScreen(){
        mainMenuScreen.show();
        setScreen(mainMenuScreen);

    }

    public IntArray getScores() {
        return hiscoreDB.getScores();
    }
}
