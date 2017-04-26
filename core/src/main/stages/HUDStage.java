package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class HUDStage extends Stage {

	private Skin skin;
	
	public HUDStage(ClickListener CL){
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		addStartButton(CL);
	}
	private void addStartButton(ClickListener CL) {
        TextButton startButton = new TextButton("Start game", skin, "default");
        startButton.setTransform(false);
        startButton.setWidth(200f);
        startButton.setHeight(20f);
        startButton.setPosition(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
        startButton.addListener(CL);

        this.addActor(startButton);
        
    }
}
