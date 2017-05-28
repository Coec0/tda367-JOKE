package com.example.illegalaliens.views.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * Stage for selecting Map.
 * @author Johan Svennungsson
 */
public class MapSelectStage extends AbstractStage {


	private ClickListener mainMenuController;
	private Actor startGameButton;
	private Actor alphaMapSelectedLabel;
	private Actor map1SelectedLabel;
	private Actor map2SelectedLabel;

	public MapSelectStage(ClickListener mainMenuController) {
		this.mainMenuController = mainMenuController;

		this.addActor(addTopLabel());
		this.addActor(addAlphaMapSelectedLabel());
		this.addActor(addMap1SelectedLabel());
		this.addActor(addMap2SelectedLabel());


		this.addActor(addMapTable());
		this.addActor(addStartGameButton());
		this.addActor(addBackButton());

		this.setVisible(false);
		showLabel("AlphaMap");
	}

	private Table addMapTable() {
		Table mapTable = new Table();

		mapTable.setWidth(400f);
		mapTable.setHeight(400f);
		mapTable.setPosition(centerWidth, centerHeight, center);

		mapTable.add(addAlphaMapButton()).width(200f).height(200f);
		mapTable.add(addMap1Button()).width(200f).height(200f);
		mapTable.add(addMap2Button()).width(200f).height(200f);

		return mapTable;
	}


	public void showLabel(String id){
		if (id.equals("map1")){
			map1SelectedLabel.setVisible(true);
			map2SelectedLabel.setVisible(false);
			alphaMapSelectedLabel.setVisible(false);

		}
		else if (id.equals("map2")){
			map2SelectedLabel.setVisible(true);
			map1SelectedLabel.setVisible(false);
			alphaMapSelectedLabel.setVisible(false);

		}
		else if (id.equals("AlphaMap")){
			alphaMapSelectedLabel.setVisible(true);
			map1SelectedLabel.setVisible(false);
			map2SelectedLabel.setVisible(false);
		}
	}

	private Actor addAlphaMapSelectedLabel(){
		alphaMapSelectedLabel = ActorFactory.createLabel("Alphamap selected", centerWidth - 225, centerHeight + 100, center);
		return alphaMapSelectedLabel;
	}
	private Actor addMap1SelectedLabel(){
		map1SelectedLabel = ActorFactory.createLabel("Map 1 selected", centerWidth , centerHeight + 100, center);
		return map1SelectedLabel;
	}
	private Actor addMap2SelectedLabel(){
		map2SelectedLabel = ActorFactory.createLabel("Map 2 selected", centerWidth + 225, centerHeight + 100, center);
		return map2SelectedLabel;
	}



	private Actor addAlphaMapButton() {
		Actor alphaMap = ActorFactory.createImageButton(new Texture("maps/AlphaMap.png"));

		alphaMap.setName("AlphaMap");
		alphaMap.addListener(mainMenuController);

		return alphaMap;
	}
	
	private Actor addMap1Button() {
		Actor map1 = ActorFactory.createImageButton(new Texture("maps/map1.png"));
		map1.setName("map1");
		map1.addListener(mainMenuController);

		return map1;
	}
	
	private Actor addMap2Button() {
		Actor map2 = ActorFactory.createImageButton(new Texture("maps/map2.png"));
		map2.setName("map2");
		map2.addListener(mainMenuController);

		return map2;
	}

	private Actor addStartGameButton() {
		startGameButton = ActorFactory.createTextButton("Start game",
				centerWidth + 100, centerHeight - 100, center);
		startGameButton.setName("startGame");
		startGameButton.addListener(mainMenuController);

		return startGameButton;
	}

	private Actor addBackButton() {
		return ActorFactory.createTextButton("backToMainMenu","Back to Main menu",
				centerWidth - 100, centerHeight - 100, center, mainMenuController);
	}

	private Actor addTopLabel() {
		return ActorFactory.createLabel("Select map", centerWidth, centerHeight + 200, center);
	}


}
