package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class RightGameUIStage extends Stage {

	private Skin skin;

	public RightGameUIStage(ClickListener alienC, ClickListener buildingC) {
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		Table table = new Table();
//		table.setDebug(true);
		table.setPosition(Gdx.graphics.getWidth() - 200, 0);
		table.setWidth(200);
		table.setHeight(720);

		table.add(getTowersTable(buildingC)).expand().top();
		table.row();
		//table.add(addNextWaveButton(alienC)).expand().bottom();
		this.addActor(table);
	}
	
	private Table getTowersTable(ClickListener buildingC){
		Table table = new Table();
		table.add(addTowerButton(620, "soldier", buildingC)).expand().top();
		table.add(addTowerButton(620, "tank", buildingC)).expand().top();
		table.row();
		table.add(addTowerButton(620, "ranger", buildingC)).expand().top();
		table.add(addTowerButton(620, "marine", buildingC)).expand().top();
		table.row();
		table.add(addTowerButton(620, "engineer", buildingC)).expand().top();
		table.add(addTowerButton(620, "howitzer", buildingC)).expand().top();
		table.row();
		table.add(addTowerButton(620, "sniper", buildingC)).expand().top();
		table.add(addTowerButton(620, "bazooka", buildingC)).expand().top();
		table.row();
		table.add(addTowerButton(620,"aliennerfer",buildingC)).expand().top();
		table.add(addTowerButton(620,"towerbooster",buildingC)).expand().top();

		return table;
	}

	private TextButton addTextButton(int x, int y, float width, float height, String name, String text,
			ClickListener CL) {
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

		return addTextButton(x, y, width, height, "nextWave", "Send next wave", CL);
	}

	private TextButton addTowerButton(int y, String name, ClickListener CL) {
		int width = 200;
		int height = 100;
		int x = Gdx.graphics.getWidth() - width;

		return addTextButton(x, y, width, height, name, "Select " + name, CL);
	}
}
