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

public class PoliticalMeterStage extends Stage {

	private final int HEIGHT = 200;
	private final int WIDTH = 500;

	public PoliticalMeterStage() {
		Table table = new Table();
		// table.setDebug(true);
		table.setPosition((Gdx.graphics.getWidth() - 200 - WIDTH) / 2, 0);
		table.setWidth(WIDTH);
		table.setHeight(HEIGHT);

		table.add(colorLabel(Color.RED)).width(WIDTH / 2);
		table.add(colorLabel(Color.BLUE)).width(WIDTH / 2);
		this.addActor(table);
	}

	private Label colorLabel(Color color) {
		Skin skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		Label label = new Label("", skin);
		Pixmap labelColor = new Pixmap(10, 10, Pixmap.Format.RGB888);
		labelColor.setColor(color);
		labelColor.fill();
		label.getStyle().background = new Image(new Texture(labelColor)).getDrawable();
		return label;
	}

}
