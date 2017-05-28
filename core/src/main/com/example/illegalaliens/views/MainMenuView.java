package com.example.illegalaliens.views;

import com.badlogic.gdx.utils.IntArray;
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

	private void showHiscoreStage(IntArray scores) {
		stageSwitcher.showStage(hiscoreStage);
		hiscoreStage.refreshHiscoreTable(scores);
	}
	
	private void showIDStage(String id, IntArray scores){
		if(id.equals("MainMenu")){
			showMainMenuStage();
		}else if(id.equals("About")){
			showAboutStage();
		}else if(id.equals("SelectMap")){
			showMapSelectStage();
		} else if (id.equals("Hiscore")) {
			showHiscoreStage(scores);
		}
	}

	@Override
	public void actOnMainMenuChange(String id, IntArray scores) {
		showIDStage(id, scores);

		if (id.equals("AlphaMap")){
			mapSelectStage.showLabel("AlphaMap");
		}
		else if (id.equals("map1")){
			mapSelectStage.showLabel("map1");
		}
		else if (id.equals("map2")){
			mapSelectStage.showLabel("map2");
		}
	}

}
