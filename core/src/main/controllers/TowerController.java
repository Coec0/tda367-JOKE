package controllers;

import com.badlogic.gdx.InputAdapter;

import models.AlienModel;
import models.TowerModel;
import utilities.Node;
import views.TowerView;

public class TowerController extends InputAdapter {

	TowerView TView;
    TowerModel TModel;
    AlienModel AModeL;

    public TowerController(TowerModel TModel, AlienModel AModel, TowerView TView){
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
