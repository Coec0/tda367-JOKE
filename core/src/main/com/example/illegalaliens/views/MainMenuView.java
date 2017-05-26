package com.example.illegalaliens.views;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.example.illegalaliens.models.MainMenuObserver;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.views.stages.AboutStage;
import com.example.illegalaliens.views.stages.MainMenuStage;
import com.example.illegalaliens.views.stages.MapSelectStage;
import com.example.illegalaliens.views.stages.StageSwitcher;

public class MainMenuView extends SimpleView implements MainMenuObserver{
	
	private MainMenuStage mainMenuStage;
	private AboutStage aboutStage;
	private MapSelectStage mapSelectStage;
	private StageSwitcher stageSwitcher;
	public MainMenuView(DrawablesCollector DC, MainMenuStage mms, AboutStage aboutStage, MapSelectStage mst) {
		super(DC);
		stageSwitcher = new StageSwitcher();
		this.mainMenuStage = mms;
		this.aboutStage = aboutStage;
		this.mapSelectStage = mst;

		addToView(mainMenuStage);
		addToView(aboutStage);
		addToView(mapSelectStage);
		mapSelectStage.setVisible(false);
		aboutStage.setVisible(false);
		stageSwitcher.showStage(mainMenuStage);
	}
	
	public void showMainMenuStage() {
		stageSwitcher.showStage(mainMenuStage);
	}

	public void showAboutStage() {
		stageSwitcher.showStage(aboutStage);
	}

	public void showMapSelectStage() {
		stageSwitcher.showStage(mapSelectStage);
	}
	
	public void showIDStage(String id){
		if(id.equals("MainMenu")){
			showMainMenuStage();
		}else if(id.equals("About")){
			showAboutStage();
		}else if(id.equals("SelectMap")){
			showMapSelectStage();
		}
	}

	@Override
	public void actOnMainMenuChange(String id) {
		showIDStage(id);
	}

}
