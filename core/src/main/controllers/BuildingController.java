package controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

import buildings.towers.Tower;
import buildings.towers.TowerFactory;
import models.AlienModel;
import models.BuildingModel;
import views.BuildingView;

public class BuildingController extends ClickListener implements InputProcessor {

	BuildingView BView;
    BuildingModel BModel;
    AlienModel AModeL;
    ProjectileController PController;
    Viewport WP;
    Tower selected;

    public BuildingController(BuildingModel BModel, AlienModel AModel, BuildingView BView, ProjectileController PController, Viewport WP){
        this.BView = BView;
        this.BModel = BModel;
        this.AModeL = AModel;
        this.PController = PController;
        this.WP = WP;
        
        BModel.addObserver(BView);
        
        BModel.createWhiteHouse(1280,360);
    }

    @Override
    public void clicked(InputEvent event, float x, float y){
		 if(event.getListenerActor().getName().equals("soldier")){
			selected = TowerFactory.createSoldier((int)x, (int)y, PController); // x and y never used
			BView.placeTexture(selected);
		 }
    }
    
    @Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	if(selected != null){
    		Vector3 v = new Vector3 (screenX , screenY, 0);
    		WP.unproject(v);
    		BModel.addTower(selected, (int)v.x,(int) v.y);
    		selected = null;
    		BView.removePlaceTexture();
    	}
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(selected != null){
			Vector3 v = new Vector3 (screenX , screenY, 0);
    		WP.unproject(v);
			BView.movePlaceTexture(v.x, v.y);
		}
		return false;
	}
    
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
