package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class EndGamePopupStage extends Stage{
	Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
	private float WIDTH = 300;
	private float HEIGHT = 350;
	
	public EndGamePopupStage(){
		Pixmap labelColor = new Pixmap(10, 10, Pixmap.Format.Alpha);
		//labelColor.setColor(Color.BLACK);
		labelColor.setColor(0, 0, 0, 0.7f);
		labelColor.fill();
		Table mainTable = new Table();
		
		mainTable.setBackground(new Image(new Texture(labelColor)).getDrawable());
		//mainTable.setPosition(Gdx.graphics.getWidth()/2 - WIDTH / 2, Gdx.graphics.getHeight()/2 - HEIGHT / 2);
		mainTable.setHeight(Gdx.graphics.getHeight());
		mainTable.setWidth(Gdx.graphics.getWidth());
		
		mainTable.debug();
		mainTable.add(getPopupTable()).center().width(WIDTH).height(HEIGHT);
		labelColor.dispose();
		this.addActor(mainTable);
		
	}
	

	private Table getBackgroundTable(){
		
		Table table = new Table();
		
		table.setHeight(Gdx.graphics.getHeight());
		table.setWidth(Gdx.graphics.getWidth());
		//table.setBackground(new Image(new Texture(labelColor)).getDrawable());
		
		return table;
	}
	
	
	private Table getPopupTable(){
		Pixmap labelColor = new Pixmap(10, 10, Pixmap.Format.RGB888);
		labelColor.setColor(Color.BLACK);
		//labelColor.setColor(0, 0, 0);
		labelColor.fill();
		Label label = new Label("GameOver Loser!" , skin);
		label.setWidth(100);
		label.setHeight(100);
		label.setPosition(150, 300);
		Table table = new Table();
		table.setBackground(new Image(new Texture(labelColor)).getDrawable());
		table.add(label);
		table.add(new TextButton("Button",skin));
		//table.row();
		labelColor.dispose();
		return table;
		
	}
}
