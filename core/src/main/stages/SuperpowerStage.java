package stages;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

import textures.PortraitTextureHandler;

/**
 * Created by Emil on 2017-05-07.
 */
public class SuperpowerStage extends Stage {


    private Skin skin;
    private Table table;
    private ImageTextButton nukeB, minutemenB, wallB, boostB;
    
    
    private final int WIDTH = 400;
    private final int HEIGHT = 100;
    public SuperpowerStage(ClickListener superC){
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"));
        this.table = new Table();
        createSuperPowerButtons(superC);

        table.setPosition(Gdx.graphics.getWidth() / 4, 0);
        table.setHeight(HEIGHT);
        table.setWidth(WIDTH);

        table.add(addSuperpowerTable(superC));
        this.addActor(table);

    }

    private Table addSuperpowerTable(ClickListener superC){

        Table table = new Table();
        table.setWidth(WIDTH);

        table.add(nukeB).width(WIDTH/4).height(HEIGHT);
        table.add(minutemenB).width(WIDTH/4).height(HEIGHT);
        table.add(wallB).width(WIDTH/4).height(HEIGHT);
        table.add(boostB).width(WIDTH/4).height(HEIGHT);

        /*
        table.add(addSuperpowerButton(100,"nuke", superC)).expand().top();
        table.add(addSuperpowerButton(200,"wall", superC));
        table.add(addSuperpowerButton(300,"minutemen", superC));
        table.add(addSuperpowerButton(400,"towerboost", superC));
*/
        return table;
    }
/*
    private TextButton addSuperpowerButton(int x, String name, ClickListener CL){
        int width = 200;
        int height = 50;
        int y = 35;
        return addTextButton(x, y, width, height, name, "Superpower: " + name, CL);
    }
*/
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
        nukeB = addSuperPowerButton("nuke", "nuke", PortraitTextureHandler.getNukePortrait(),superC);
        minutemenB = addSuperPowerButton("minutemen", "minutemen", PortraitTextureHandler.getMinutemenPortrait(), superC);
        boostB = addSuperPowerButton("towerboost", "towerboost",PortraitTextureHandler.getBoostPortrait(), superC);
        wallB = addSuperPowerButton("wall","wall", PortraitTextureHandler.getWallPortrait(), superC);

    }
    
    private void disableITB(Touchable disable, ImageTextButton button){
    	button.setTouchable(disable);
    	if(disable == Touchable.disabled){
    		button.setColor(Color.RED);
    	}else{
    		button.setColor(Color.WHITE);
    	}
    }
    
    public void disableNuke(Touchable disable){
    	disableITB(disable, nukeB);
    }
    
    public void disableMinuteman(Touchable disable){
    	disableITB(disable, minutemenB);
    }
    
    public void disableWall(Touchable disable){
    	disableITB(disable, wallB);
    }
    
    public void disableTowerBoost(Touchable disable){
    	disableITB(disable, boostB);
    }

    private ImageTextButton addSuperPowerButton(String name, String text ,Texture texture, ClickListener CL) {
        return addImageTextButton(name, text, texture, CL);
    }

    private TextButton addTextButton(int x, int y, float width, float height, String name, String text ,ClickListener CL){
        TextButton textButton = new TextButton(text, skin, "default");

        textButton.setName(name);
        textButton.setTransform(false);
        textButton.setWidth(width);
        textButton.setHeight(height);
        textButton.setPosition(x, y);
        textButton.addListener(CL);

        return textButton;
    }
}
