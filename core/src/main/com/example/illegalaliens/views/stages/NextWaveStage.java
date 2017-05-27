package com.example.illegalaliens.views.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class NextWaveStage extends Stage{
	private Skin skin;
	
	public NextWaveStage(ClickListener CL){
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		this.addActor(addNextWaveButton(CL));
	}
	
	
	private TextButton addTextButton(int x, int y, String name, String text,
			ClickListener CL) {
		TextButton textButton = new TextButton(text, skin, "default");

		textButton.setName(name);
		textButton.setTransform(false);
		textButton.setPosition(x, y);
		textButton.setSize(200,113);
		textButton.getLabel().setFontScale(1.3f);
		textButton.addListener(CL);

		return textButton;
	}

	private TextButton addNextWaveButton(ClickListener CL) {
		int x = Gdx.graphics.getWidth() - 200;
		int y = 0;
		return addTextButton(x, y, "nextWave", "Send next wave", CL);
	}
}
