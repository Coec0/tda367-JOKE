package com.example.illegalaliens.views;

import com.example.illegalaliens.models.MainMenuObserver;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.views.stages.*;

public class MainMenuView extends SimpleView implements MainMenuObserver{
	
	private MainMenuStage mainMenuStage;
	private AboutStage aboutStage;
	private MapSelectStage mapSelectStage;
	private HiscoreStage hiscoreStage;
	private StageSwitcher stageSwitcher;
	public MainMenuView(DrawablesCollector DC, MainMenuStage mms, AboutStage aboutStage,
						MapSelectStage mst, HiscoreStage hiscoreStage) {
		super(DC);
		stageSwitcher = new StageSwitcher();
		this.mainMenuStage = mms;
		this.aboutStage = aboutStage;
		this.mapSelectStage = mst;
		this.hiscoreStage = hiscoreStage;

		addToView(mainMenuStage);
		addToView(aboutStage);
		addToView(mapSelectStage);
		addToView(hiscoreStage);
		mapSelectStage.setVisible(false);
		aboutStage.setVisible(false);
		hiscoreStage.setVisible(false);
		stageSwitcher.showStage(mainMenuStage);
	}
	
	private void showMainMenuStage() {
		stageSwitcher.showStage(mainMenuStage);
	}

	private void showAboutStage() {
		stageSwitcher.showStage(aboutStage);
	}

	private void showMapSelectStage() {
		stageSwitcher.showStage(mapSelectStage);
	}

	private void showHiscoreStage() {
		stageSwitcher.showStage(hiscoreStage);
	}
	
	private void showIDStage(String id){
		if(id.equals("MainMenu")){
			showMainMenuStage();
		}else if(id.equals("About")){
			showAboutStage();
		}else if(id.equals("SelectMap")){
			showMapSelectStage();
		} else if (id.equals("Hiscore")) {
			showHiscoreStage();
		}
	}

	@Override
	public void actOnMainMenuChange(String id) {
		showIDStage(id);
	}

}
