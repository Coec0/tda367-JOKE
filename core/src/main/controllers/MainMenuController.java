package controllers;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import screens.MainMenuScreen;

public class MainMenuController extends ClickListener {

	private MainMenuScreen mainMenuScreen;

	public MainMenuController(MainMenuScreen mainMenuScreen) {
		this.mainMenuScreen = mainMenuScreen;
	}

	@Override
	public void clicked(InputEvent event, float x, float y) {
		if (event.getListenerActor().getName().equals("backToMainMenu")) {
			mainMenuScreen.showMainMenuStage();
		} else if (event.getListenerActor().getName().equals("mapSelectStage")) {
			mainMenuScreen.showMapSelectStage();
		} else if (event.getListenerActor().getName().equals("aboutStage")) {
			mainMenuScreen.showAboutStage();
		}
	}
}
