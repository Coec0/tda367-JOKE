package com.example.illegalaliens.controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.example.illegalaliens.models.AlienModel;
import com.example.illegalaliens.models.BoardObjectModel;
import com.example.illegalaliens.models.boardobjects.Wall;
import com.example.illegalaliens.models.superpowers.SuperpowerModel;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.path.PathFinder;

/**
 * Created by Emil on 2017-05-04.
 */
public class SuperpowerController extends ClickListener implements InputProcessor {

    private SuperpowerModel SModel;
    private AlienModel AModel;
    private BoardObjectModel BOModel;
    private Viewport VP;

    private PathFinder finder;

    public SuperpowerController(SuperpowerModel SModel, Viewport VP, AlienModel AModel, PathFinder finder, BoardObjectModel BOModel ){
    	this.finder = finder;
        this.SModel = SModel;
        this.VP = VP;
        this.AModel = AModel;
        this.BOModel = BOModel;
    }

    @Override
    public void clicked(InputEvent event, float x, float y){
        if(event.getListenerActor().getName().equals("nuke")){
            SModel.useNuke(AModel.getAllEnemies());
        }
        if(event.getListenerActor().getName().equals("wall")){
        	BOModel.clickedBuilding(SModel.getWall((int) x, (int) y)); 
        }
        if(event.getListenerActor().getName().equals("minutemen")){
            SModel.useMinutemen();
        }
        if(event.getListenerActor().getName().equals("towerboost")){
            SModel.useTowerBoost(BOModel.getTowers());
        }
    }
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	Vector3 v = new Vector3 (screenX , screenY, 0);
		VP.unproject(v);
		
		if(v.x >= VP.getWorldWidth()) //Makes sure you cant click on ui
			return false;
		
    	if(BOModel.getHighlighted() != null && BOModel.getHighlighted() instanceof Wall && !BOModel.getHighlighted().isActive() && finder.isOnRoad(new Node((int)v.x,(int)v.y), 1)){
    		SModel.useWall((int)v.x, (int)v.y);
    	}
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
