package controllers;

import com.badlogic.gdx.InputAdapter;

import models.AlienModel;
import models.BuildingModel;
import utilities.Node;
import views.BuildingView;

public class BuildingController extends InputAdapter {

	BuildingView TView;
    BuildingModel TModel;
    AlienModel AModeL;

    public BuildingController(BuildingModel TModel, AlienModel AModel, BuildingView TView){
        this.TView = TView;
        this.TModel = TModel;
        this.AModeL = AModel;
        
    }

    @Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
    	TModel.createSoldier(new Node (screenX, screenY));
        TView.addToView(TModel.peekTower().getSpriteAdapter());
		return super.touchDown(screenX, screenY, pointer, button);
	}

}
