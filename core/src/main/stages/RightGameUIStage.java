package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class RightGameUIStage extends Stage {

	private Skin skin;
	private Texture soldier,tank, ranger, sniper, aliennerfer, riotshield;

	public RightGameUIStage(ClickListener alienC, ClickListener buildingC) {
		soldier = new Texture("solider512.png");
        tank = new Texture("tank256.png");
        ranger = new Texture("ranger256.png");
        sniper = new Texture("sniper512.png");
        aliennerfer = new Texture("soldier.png");
        riotshield = new Texture("riotshield256.png");
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
		table.setWidth(200);
		table.add(addTowerButton(620, "soldier", soldier, buildingC)).width(100).height(100);
		table.add(addTowerButton(620, "tank", tank ,buildingC)).width(100).height(100);
		table.row();
		table.add(addTowerButton(620, "ranger", ranger, buildingC)).width(100).height(100);
		table.add(addTowerButton(620, "riotshield", riotshield, buildingC)).width(100).height(100);//.expand().top();
		table.row();
		table.add(addTowerButton(620, "sniper", sniper, buildingC)).width(100).height(100);//.expand().top();table.row();
		table.add(addTowerButton(620,"aliennerfer", aliennerfer,buildingC)).width(100).height(100);//.expand().top();

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
	
	private ImageTextButton addImageButton(String name, String text, Texture texture,ClickListener CL){
		Skin skin = new Skin(Gdx.files.internal("ui/skin/plain-james-ui.json"));
		ImageTextButton imageButton = new ImageTextButton("", skin); //TODO add text for cost
		imageButton.getLabel().setFontScale(0.2f);
		imageButton.setName(name);
		imageButton.addListener(CL);
		imageButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(texture));
		imageButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(texture));
		return imageButton;
	}

	private TextButton addNextWaveButton(ClickListener CL) {
		int width = 200;
		int height = 50;
		int x = Gdx.graphics.getWidth() - width;
		int y = 0;

		return addTextButton(x, y, width, height, "nextWave", "Send next wave", CL);
	}

	private ImageTextButton addTowerButton(int y, String name, Texture texture, ClickListener CL) {
		//int width = 200;
		//int height = 100;
		//int x = Gdx.graphics.getWidth() - width;

		return addImageButton(name, "Tower", texture, CL);
	}
}
