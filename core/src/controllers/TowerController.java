package controllers;

import models.AlienModel;
import models.TowerModel;
import utilities.Node;
import utilities.SpriteAdapter;
import views.TowerView;

public class TowerController {
    TowerView TView;
    TowerModel TModel;
    AlienModel AModeL;

    public TowerController(TowerModel TModel, AlienModel AModel, TowerView TView){
        this.TView = TView;
        this.TModel = TModel;
        this.AModeL = AModel;
        TModel.createSoldier(new Node (2, 3));
        TView.addToView(TModel.getTower(0).getSpriteAdapter());
    }


}
