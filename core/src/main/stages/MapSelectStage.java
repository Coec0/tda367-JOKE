package stages;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.illegalaliens.IllegalAliensMain;
import factories.ActorFactory;
import map.Map;

/**
 * Stage for selecting Map.
 * @author Johan Svennungsson
 */
public class MapSelectStage extends AbstractStage {

	private IllegalAliensMain game;
	private StageSwitcher stageSwitcher;
	private Map map;

	private Actor startGameButton;

	public MapSelectStage(IllegalAliensMain game, StageSwitcher stageSwitcher) {
		this.game = game;
		this.stageSwitcher = stageSwitcher;

		this.addActor(addTopLabel());
		this.addActor(addMapTable());
		this.addActor(addStartGameButton());
		this.addActor(addBackButton());
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

		alphaMap.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y){
				map = new Map("AlphaMap", new Texture("maps/AlphaMap.png"));
				startGameButton.fire(new ChangeListener.ChangeEvent()); //button should be enabled
			}
		});

		return alphaMap;
	}
	
	private Actor addMap1Button() {
		Actor alphaMap = ActorFactory.createImageButton(new Texture("maps/map1.png"));

		alphaMap.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y){
				map = new Map("map1", new Texture("maps/map1.png"));
				startGameButton.fire(new ChangeListener.ChangeEvent()); //button should be enabled
			}
		});

		return alphaMap;
	}
	
	private Actor addMap2Button() {
		Actor alphaMap = ActorFactory.createImageButton(new Texture("maps/map2.png"));

		alphaMap.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y){
				map = new Map("map2", new Texture("maps/map2.png"));
				startGameButton.fire(new ChangeListener.ChangeEvent()); //button should be enabled
			}
		});

		return alphaMap;
	}

	private Actor addStartGameButton() {
		startGameButton = ActorFactory.createTextButton("Start game",
				centerWidth + 100, centerHeight - 100, center);

		startGameButton.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				//TODO: make button enabled
			}
		});

		startGameButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y){
				game.startGame(map);
			}
		});

		return startGameButton;
	}

	private Actor addBackButton() {
		Actor backButton = ActorFactory.createTextButton("Back to Main menu", centerWidth - 100, centerHeight - 100, center);

		backButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				stageSwitcher.showStage(stageSwitcher.getMainMenuStage());
			}
		});

		return backButton;
	}

	private Actor addTopLabel() {
		return ActorFactory.createLabel("Select map", centerWidth, centerHeight + 100, center);
	}


}
