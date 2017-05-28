package com.example.illegalaliens.views.stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.example.illegalaliens.views.textures.PortraitTextureHandler;

/**
 * Created by Emil on 2017-05-07.
 */
public class SuperpowerStage extends Stage {

    private Table table;
    private ImageTextButton nukeB, minutemenB, wallB, boostB;


    private final int WIDTH = 450;
    private final int HEIGHT = 70;
    public SuperpowerStage(ClickListener superC){
        this.table = new Table();

        createSuperPowerButtons(superC);

        table.setPosition((Gdx.graphics.getWidth()-200-WIDTH) / 2, 0);
        table.setHeight(HEIGHT);
        table.setWidth(WIDTH);

        table.add(addSuperpowerTable());
        disableMinutemen(true);
        disableWall(true);
        disableNuke(true);
        disableTowerBoost(true);
        this.addActor(table);
        


    }

    private Table addSuperpowerTable(){

        Table table = new Table();
        table.setWidth(WIDTH);

        table.add(nukeB).width(WIDTH/4).height(HEIGHT);
        table.add(minutemenB).width(WIDTH/4).height(HEIGHT);
        table.add().width(WIDTH/8).height(HEIGHT);
        table.add(wallB).width(WIDTH/4).height(HEIGHT);
        table.add(boostB).width(WIDTH/4).height(HEIGHT);
        return table;
    }

    private ImageTextButton addImageTextButton(String name, String text, Texture texture, ClickListener CL){
        Skin skin = new Skin(Gdx.files.internal("ui/skin/plain-james-ui.json"));
        ImageTextButton imageButton = new ImageTextButton(text, skin);
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

    private void createSuperPowerButtons(ClickListener superC){
        nukeB = addSuperPowerButton("nuke", "Nuke", PortraitTextureHandler.getNukePortrait(),superC);
        nukeB.setColor(Color.BLUE);

        minutemenB = addSuperPowerButton("minutemen", "Minutemen", PortraitTextureHandler.getMinutemenPortrait(), superC);
        minutemenB.setColor(Color.BLUE);


        boostB = addSuperPowerButton("towerboost", "Towerboost",PortraitTextureHandler.getBoostPortrait(), superC);
        boostB.setColor(Color.RED);

        wallB = addSuperPowerButton("wall","Wall", PortraitTextureHandler.getWallPortrait(), superC);
        wallB.setColor(Color.RED);
    }
    
    
    
    private void disableITB(boolean disable, ImageTextButton button){
    	if(disable)
			button.setTouchable(Touchable.disabled);
		else
			button.setTouchable(Touchable.enabled);
    	if(disable){
    		button.getColor().a = 0.7f;
    	}else{
    		button.getColor().a = 1f;
    	}
    }
    
    public void disableNuke(boolean disable){
    	disableITB(disable, nukeB);
    }
    
    public void disableMinutemen(boolean disable){
    	disableITB(disable, minutemenB);
    }
    
    public void disableWall(boolean disable){
    	disableITB(disable, wallB);
    }
    
    public void disableTowerBoost(boolean disable){
    	disableITB(disable, boostB);
    }

    public void updateNukeCost(int cost){
    	nukeB.setText("Nuke (" + cost+")" );
    }
    
    public void updateMinuteMenCost(int cost){
    	minutemenB.setText("Minutemen (" + cost+")" );
    }
    
    public void updateWallCost(int cost){
    	wallB.setText("Wall (" + cost+")" );
    }
    
    public void updateTowerBoostCost(int cost){
    	boostB.setText("Towerboost (" + cost+")" );
    }
    
    private ImageTextButton addSuperPowerButton(String name, String text ,Texture texture, ClickListener CL) {
        return addImageTextButton(name, text, texture, CL);
    }

}
