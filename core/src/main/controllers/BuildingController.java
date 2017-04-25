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

    public BuildingController(BuildingModel BModel, AlienModel AModel, BuildingView BView){
        this.BView = BView;
        this.BModel = BModel;
        this.AModeL = AModel;
        
        BModel.addObserver(BView);
        
        BModel.createWhiteHouse(1280,360);
    }

    @Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	BModel.addTower(TowerFactory.createSoldier(screenX, screenY));
		return super.touchDown(screenX, screenY, pointer, button);
	}

}
