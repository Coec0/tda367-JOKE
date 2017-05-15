package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.IllegalAliensMain;
import interfaces.IStage;

/**
 * Class for switching between Stages in MainMenuScreen
 * @author Johan Svennungsson
 */
public class StageSwitcher extends AbstractStage {

	private Array<IStage> stages = new Array<IStage>();

	private MainMenuStage mainMenuStage;
	private AboutStage aboutStage;
	private MapSelectStage mapSelectStage;

	public StageSwitcher(IllegalAliensMain game) {
		mainMenuStage = new MainMenuStage(game, this);
		aboutStage = new AboutStage(game, this);
		mapSelectStage = new MapSelectStage(game, this);

		stages.addAll(mainMenuStage, aboutStage, mapSelectStage);
	}

	public void drawStages() {
		for (IStage stage : stages) {
			stage.draw();
		}
	}

	/**
	 * Show a stage.
	 * @param stage
	 */
	public void showStage(IStage stage){
		for(Actor actor : stage.getActors()){
			actor.setVisible(true);
		}

		this.hideAllStagesExcept(stage);

		Gdx.input.setInputProcessor((InputProcessor) stage);
	}

	/**
	 * Hide all stages except the one specified.
	 * @param s
	 */
	private void hideAllStagesExcept(IStage s) {
		for (IStage stage : stages) {
			stage.setVisible(false);
		}
		s.setVisible(true);
	}

	public IStage getMainMenuStage() {
		return mainMenuStage;
	}

	public IStage getAboutStage() {
		return aboutStage;
	}

	public IStage getMapSelectStage() {
		return mapSelectStage;
	}
}
