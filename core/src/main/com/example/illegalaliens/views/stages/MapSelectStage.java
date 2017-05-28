package com.example.illegalaliens.views.stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.utilities.path.map.Map;

/**
 * Stage for selecting Map.
 * @author Johan Svennungsson
 */
public class MapSelectStage extends AbstractStage {


	private ClickListener mainMenuController;
	private Actor startGameButton;

	public MapSelectStage(ClickListener mainMenuController) {
		this.mainMenuController = mainMenuController;

		this.addActor(addTopLabel());
		this.addActor(addMapTable());
		this.addActor(addStartGameButton());
		this.addActor(addBackButton());

		this.setVisible(false);
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
		return ActorFactory.createLabel("Select map", centerWidth, centerHeight + 100, center);
	}


}
