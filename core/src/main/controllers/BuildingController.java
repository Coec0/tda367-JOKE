package controllers;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;

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
		 if(event.getTarget().getParent().getName().equals("soldier")){
			System.out.println("SELECTS SOLDIER");
		 }
    }
    
    @Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	Vector3 v = new Vector3 (screenX , screenY, 0);
    	WP.unproject(v);
    	BModel.addTower(TowerFactory.createSoldier((int)v.x, (int)v.y, PController));
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
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
