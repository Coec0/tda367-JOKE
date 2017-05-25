package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import interfaces.IStage;

/**
 * Class for switching between Stages in MainMenuScreen
 * @author Johan Svennungsson
 */
public class StageSwitcher {

	private IStage previousStage;

	/**
	 * Show a stage.
	 * @param stage
	 */
	public void showStage(IStage stage) {
		this.hidePreviousStage();

		for(Actor actor : stage.getActors()){
			actor.setVisible(true);
		}

		previousStage = stage;

		Gdx.input.setInputProcessor((InputProcessor) stage);
	}

	private void hidePreviousStage() {
		if (previousStage != null) {
			previousStage.setVisible(false);
		}
	}
}
