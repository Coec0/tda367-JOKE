package com.example.illegalaliens.views.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.example.illegalaliens.models.boardobjects.towers.BOPrototypes;
import com.example.illegalaliens.views.textures.TowerTextureHandler;

public class RightGameUIStage extends Stage {

	private Skin skin;
	//  *Buildings/Towers*

	private ImageTextButton soldierB,tankB, rangerB, sniperB, netGunnerB, riotshieldB;
	
	//  *Executive orders*
	private TextButton civilWarRep, civilWarDem, obamaCare, taxCut, declareWar, openBorders;
	
	private static final int WIDTH = 200;
	
	public RightGameUIStage(ClickListener buildingC, ClickListener executiveOrdersC) {
		skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
		
		createPurchablesButtons(buildingC);
		createExecutiveOrdersButtons(executiveOrdersC);
		Table table = new Table();
//		table.setDebug(true);
		table.setPosition(Gdx.graphics.getWidth() - WIDTH, 0);
		table.setWidth(WIDTH);
		table.setHeight(Gdx.graphics.getHeight());

		table.add(getTowersTable()).expand().top();
		table.row();
		table.add(getExecutivesTable()).expand().top();
		this.addActor(table);
	}
	
	private Table getExecutivesTable(){
		Table table = new Table();
		table.setWidth(WIDTH);
		table.add(civilWarRep).width(WIDTH/2);
		table.add(civilWarDem).width(WIDTH/2);
		table.row();
		table.add(obamaCare).width(WIDTH/2);
		table.add(taxCut).width(WIDTH/2);
		table.row();
		table.add(openBorders).width(WIDTH/2);
		table.add(declareWar).width(WIDTH/2);
		return table;
	}
	
	private void disableButton(boolean disable, Button button){
		if(disable)
			button.setTouchable(Touchable.disabled);
		else
			button.setTouchable(Touchable.enabled);
    	if(disable == true){
    		button.getColor().a = 0.7f;
    	}else{
    		button.getColor().a = 1f;
    	}
    }
    
	public void disableSoldier(boolean disable){
		disableButton(disable, soldierB);
	}
	
	public void disableTank(boolean disable){
		disableButton(disable, tankB);
	}
	
	public void disableRanger(boolean disable){
		disableButton(disable, rangerB);
	}
	
	public void disableRiotShield(boolean disable){
		disableButton(disable, riotshieldB);
	}
	
	public void disableNetGunner(boolean disable){
		disableButton(disable, netGunnerB);
	}
	
	public void disableSniper(boolean disable){
		disableButton(disable, sniperB);
	}
	
    public void disableCivilWar(boolean disable){
    	disableButton(disable, civilWarRep);
    	disableButton(disable, civilWarDem);
    }
    
    public void disableOpenBorders(boolean disable){
    	disableButton(disable, openBorders);
    }
    
    public void disableTowerChanger(boolean disable){
    	disableButton(disable, taxCut);
    	disableButton(disable, obamaCare);
    }
    
    public void disableDeclareWar(boolean disable){
    	disableButton(disable, declareWar);
    }
	
	private void createExecutiveOrdersButtons(ClickListener executiveOrdersC){
		civilWarDem = addTextButton("CWD", "CivilWar (DEM)", executiveOrdersC);
		civilWarDem.setColor(Color.RED);

		civilWarRep = addTextButton("CWR", "CivilWar (REP)", executiveOrdersC);
		civilWarRep.setColor(Color.BLUE);

		obamaCare = addTextButton("OC", "Obama Care", executiveOrdersC);
		obamaCare.setColor(Color.BLUE);

		taxCut = addTextButton("TC", "Tax Cut", executiveOrdersC);
		taxCut.setColor(Color.RED);

		openBorders = addTextButton("OB", "Open Borders", executiveOrdersC);
		openBorders.setColor(Color.BLUE);

		declareWar = addTextButton("DW", "Declare War", executiveOrdersC);
		declareWar.setColor(Color.RED);
	}
	
	public void updatePurchables(BOPrototypes protos){
		soldierB.setText(String.valueOf((protos.getSoldier(0, 0).getCost())));
		tankB.setText(String.valueOf((protos.getTank(0, 0).getCost())));
		rangerB.setText(String.valueOf((protos.getRanger(0, 0).getCost())));
		riotshieldB.setText(String.valueOf((protos.getRiotShield(0, 0).getCost())));
		sniperB.setText(String.valueOf((protos.getSniper(0, 0).getCost())));
		netGunnerB.setText(String.valueOf((protos.getNetGunner(0, 0).getCost())));
	}
	
	private void createPurchablesButtons(ClickListener buildingC){
		soldierB = addTowerButton(620, "soldier", TowerTextureHandler.getSoldierTexture(), buildingC);
		soldierB.setColor(Color.RED);
		tankB = addTowerButton(620, "tank", TowerTextureHandler.getTankTexture(),buildingC);
		tankB.setColor(Color.BLUE);
		rangerB = addTowerButton(620, "ranger", TowerTextureHandler.getRangerTexture(), buildingC);
		rangerB.setColor(Color.RED);
		riotshieldB = addTowerButton(620, "riotshield", TowerTextureHandler.getRiotshieldTexture(), buildingC);
		riotshieldB.setColor(Color.BLUE);
		sniperB = addTowerButton(620, "sniper", TowerTextureHandler.getSniperTexture(), buildingC);
		sniperB.setColor(Color.RED);
		netGunnerB = addTowerButton(620,"netgunner", TowerTextureHandler.getNetGunnerTexture(),buildingC);
		netGunnerB.setColor(Color.BLUE);
	}
	
	private Table getTowersTable(){
		Table table = new Table();
		table.setWidth(WIDTH);
		table.add(tankB).width(WIDTH/2).height(WIDTH/2);
		table.add(soldierB).width(WIDTH/2).height(WIDTH/2);
		table.row();
		table.add(riotshieldB).width(WIDTH/2).height(WIDTH/2);

		table.add(rangerB).width(WIDTH/2).height(WIDTH/2);
		table.row();
		table.add(netGunnerB).width(WIDTH/2).height(WIDTH/2);

		table.add(sniperB).width(WIDTH/2).height(WIDTH/2);
		return table;
	}

	private TextButton addTextButton(String name, String text, ClickListener CL) {
		TextButton textButton = new TextButton(text, skin, "default");
		textButton.setName(name);
		textButton.setTransform(false);
		textButton.addListener(CL);
		textButton.getLabel().setFontScale(0.8f);
		return textButton;
	}
	
	private ImageTextButton addImageTextButton(String name, String text, Texture texture,ClickListener CL){
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
		return addImageTextButton(name, "Tower", texture, CL);
	}
}
