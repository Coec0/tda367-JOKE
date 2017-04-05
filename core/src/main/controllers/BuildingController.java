package controllers;

import com.badlogic.gdx.InputAdapter;

import models.AlienModel;
import models.BuildingModel;
import utilities.Node;
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
    }

    @Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	BModel.createSoldier(new Node (screenX, screenY));
		return super.touchDown(screenX, screenY, pointer, button);
	}

}
