package controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import buildings.BoardObject;
import buildings.Wall;
import models.AlienModel;
import models.BuildingModel;
import models.SuperpowerModel;
import path.PathFinder;
import towers.BOPrototypes;
import utilities.Node;

/**
 * Created by Emil on 2017-05-04.
 */
public class SuperpowerController extends ClickListener implements InputProcessor {

    private SuperpowerModel SModel;
    private AlienModel AModel;
    private BuildingModel BModel;
    private Viewport VP;
//    private BoardObject onMouse;
    private BOPrototypes prototypes;

    private PathFinder finder;

    public SuperpowerController(SuperpowerModel SModel, Viewport VP, AlienModel AModel, PathFinder finder, BuildingModel BModel, BOPrototypes prototypes){
    	this.finder = finder;
        this.SModel = SModel;
        this.VP = VP;
        this.AModel = AModel;
        this.BModel = BModel;
        this.prototypes = prototypes;
    }

    @Override
    public void clicked(InputEvent event, float x, float y){
        if(event.getListenerActor().getName().equals("nuke")){
            SModel.useNuke(AModel.getAllEnemies());
        }
        if(event.getListenerActor().getName().equals("wall")){
        	BModel.clickedBuilding(new Wall("wall", (int) x, (int)y, 5)); 
        }
        if(event.getListenerActor().getName().equals("minutemen")){
            SModel.useMinutemen(prototypes);
        }
        if(event.getListenerActor().getName().equals("towerboost")){
            SModel.useTowerBoost(BModel.getTowers());
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
		
    	if(BModel.getHighlighted() != null && BModel.getHighlighted() instanceof Wall && finder.isOnRoad(new Node((int)v.x,(int)v.y), 1)){
    		SModel.useWall((int)v.x, (int)v.y);
//    		onMouse = null;
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
