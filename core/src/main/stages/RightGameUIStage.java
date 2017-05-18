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
import com.badlogic.gdx.utils.Align;

import towers.BOPrototypes;

public class RightGameUIStage extends Stage {

	private Skin skin;
	private Texture soldier,tank, ranger, sniper, aliennerfer, riotshield;
	private ImageTextButton soldierB,tankB, rangerB, sniperB, aliennerferB, riotshieldB;
	
	public RightGameUIStage(ClickListener alienC, ClickListener buildingC, BOPrototypes protos) {
		soldier = new Texture("solider512.png");
        tank = new Texture("tank256.png");
        ranger = new Texture("ranger256.png");
        sniper = new Texture("sniper512.png");
        aliennerfer = new Texture("soldier.png");
        riotshield = new Texture("riotshield256.png");
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		
		createButtons(buildingC);
		updatePurchables(protos);
		Table table = new Table();
//		table.setDebug(true);
		table.setPosition(Gdx.graphics.getWidth() - 200, 0);
		table.setWidth(200);
		table.setHeight(720);

		table.add(getTowersTable()).expand().top();
		table.row();
		//table.add(addNextWaveButton(alienC)).expand().bottom();
		this.addActor(table);
	}
	
	public void updatePurchables(BOPrototypes protos){
		soldierB.setText(String.valueOf((protos.getSoldier(0, 0).getCost())));
		tankB.setText(String.valueOf((protos.getTank(0, 0).getCost())));
		rangerB.setText(String.valueOf((protos.getRanger(0, 0).getCost())));
		riotshieldB.setText(String.valueOf((protos.getRiotShield(0, 0).getCost())));
		sniperB.setText(String.valueOf((protos.getSniper(0, 0).getCost())));
		aliennerferB.setText(String.valueOf((protos.getAlienNerfer(0, 0).getCost())));
	}
	
	private void createButtons(ClickListener buildingC){
		soldierB = addTowerButton(620, "soldier", soldier, buildingC);
		tankB = addTowerButton(620, "tank", tank ,buildingC);
		rangerB = addTowerButton(620, "ranger", ranger, buildingC);
		riotshieldB = addTowerButton(620, "riotshield", riotshield, buildingC);
		sniperB = addTowerButton(620, "sniper", sniper, buildingC);
		aliennerferB = addTowerButton(620,"aliennerfer", aliennerfer,buildingC);
	}
	
	private Table getTowersTable(){
		Table table = new Table();
		table.setWidth(200);
		table.add(soldierB).width(100).height(100);
		table.add(tankB).width(100).height(100);
		table.row();
		table.add(rangerB).width(100).height(100);
		table.add(riotshieldB).width(100).height(100);//.expand().top();
		table.row();
		table.add(sniperB).width(100).height(100);//.expand().top();table.row();
		table.add(aliennerferB).width(100).height(100);//.expand().top();

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
		ImageTextButton imageButton = new ImageTextButton("$$$", skin);
		imageButton.stack().row();
		imageButton.getImageCell().colspan(2);
		imageButton.stack(imageButton.getLabel()).align(Align.center).expand().fill();
		imageButton.getLabel().setFontScale(0.5f);
		imageButton.setName(name);
		imageButton.addListener(CL);
		imageButton.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(texture));
		imageButton.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(texture));
		imageButton.pack();
		imageButton.invalidateHierarchy();
		return imageButton;
	}

	private ImageTextButton addTowerButton(int y, String name, Texture texture, ClickListener CL) {
		//int width = 200;
		//int height = 100;
		//int x = Gdx.graphics.getWidth() - width;

		return addImageButton(name, "Tower", texture, CL);
	}
}
