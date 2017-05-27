package com.example.illegalaliens.views.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class NextWaveStage extends Stage{
	private Skin skin;
	private TextButton nwB;
	
	public NextWaveStage(ClickListener CL){
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		nwB = addNextWaveButton(CL);
		this.addActor(nwB);
	}
	
	public void disableButton(boolean disable){
		if(disable){
			nwB.setTouchable(Touchable.disabled);
			nwB.setColor(Color.GRAY);
		} else {
			nwB.setTouchable(Touchable.enabled);
			nwB.setColor(Color.GREEN);
		}
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
		textButton.setColor(Color.GREEN);

		return textButton;
	}

	private TextButton addNextWaveButton(ClickListener CL) {
		int x = Gdx.graphics.getWidth() - 200;
		int y = 0;
		return addTextButton(x, y, "nextWave", "Send next wave", CL);
	}
}
