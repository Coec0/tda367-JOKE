package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class GameUIStage extends Stage {

	private Skin skin;
	
	public GameUIStage(ClickListener AlienC, ClickListener BuildingC){
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		nextWaveButton(AlienC);
		towerButton(620, "soldier", BuildingC);
		
	}
	private void addButton(int x, int y, float width, float height, String name, String text, ClickListener CL) {
        TextButton Button = new TextButton(text, skin, "default");
        Button.setName(name);
        Button.setTransform(false);
        Button.setWidth(width);
        Button.setHeight(height);
        Button.setPosition(x, y);
        Button.addListener(CL);
        this.addActor(Button);
        
    }
	
	private void nextWaveButton(ClickListener CL){
		int nWaveWidth = 300;
		int nWaveHeight = 50;
		int nWaveX = Gdx.graphics.getWidth()-nWaveWidth;
		int nWaveY = 0;
		addButton(nWaveX, nWaveY, nWaveWidth, nWaveHeight,"nextWave", "Send next wave", CL);
	}
	
	private void towerButton(int y, String name, ClickListener CL){
		int Width = 200;
		int Height = 100;
		int X = Gdx.graphics.getWidth()-Width;
		int Y = y;
		addButton(X, Y, Width, Height, name, "Select "+name, CL);
		
	}
}
