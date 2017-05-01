package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameUIStage extends Stage {

	private Skin skin;
	
	public GameUIStage(ClickListener alienC, ClickListener buildingC){
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));

		this.addActor(addNextWaveButton(alienC));
		this.addActor(addTowerButton(620, "soldier", buildingC));
	}

	private TextButton addTextButton(int x, int y, float width, float height, String name, String text, ClickListener CL) {
		TextButton textButton = new TextButton(text, skin, "default");

		textButton.setName(name);
		textButton.setTransform(false);
		textButton.setWidth(width);
		textButton.setHeight(height);
		textButton.setPosition(x, y);
		textButton.addListener(CL);

		return textButton;
	}

	private TextButton addNextWaveButton(ClickListener CL) {
		int width = 200;
		int height = 50;
		int x = Gdx.graphics.getWidth() - width;
		int y = 0;

		return addTextButton(x, y, width, height,"nextWave", "Send next wave", CL);
	}

	private TextButton addTowerButton(int y, String name, ClickListener CL) {
		int width = 200;
		int height = 100;
		int x = Gdx.graphics.getWidth() - width;

		return addTextButton(x, y, width, height, name, "Select "+name, CL);
	}
}
