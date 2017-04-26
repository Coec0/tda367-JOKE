package controllers;

import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.viewport.Viewport;

import buildings.towers.TowerFactory;
import models.AlienModel;
import models.BuildingModel;
import views.BuildingView;

public class BuildingController extends InputAdapter {

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
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	Vector3 v = new Vector3 (screenX , screenY, 0);
    	WP.unproject(v);
    	BModel.addTower(TowerFactory.createSoldier((int)v.x, (int)v.y, PController));
		return super.touchDown((int)v.x, (int)v.y, pointer, button);
	}

}
