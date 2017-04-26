package controllers;

import com.badlogic.gdx.InputAdapter;

import buildings.towers.TowerFactory;
import models.AlienModel;
import models.BuildingModel;
import views.BuildingView;

public class BuildingController extends InputAdapter {

	BuildingView BView;
    BuildingModel BModel;
    AlienModel AModeL;
    ProjectileController PController;

    public BuildingController(BuildingModel BModel, AlienModel AModel, BuildingView BView, ProjectileController PController){
        this.BView = BView;
        this.BModel = BModel;
        this.AModeL = AModel;
        this.PController = PController;
        
        BModel.addObserver(BView);
        
        BModel.createWhiteHouse(1280,360);
    }

    @Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	BModel.addTower(TowerFactory.createSoldier(screenX, screenY, PController));
		return super.touchDown(screenX, screenY, pointer, button);
	}

}
