package controllers;

import models.AlienModel;
import models.TowerModel;
import views.TowerView;

public class TowerController {
    TowerView TView;
    TowerModel TModel;
    AlienModel AModeL;

    public TowerController(TowerModel TModel, AlienModel AModel, TowerView TView){
        this.TView = TView;
        this.TModel = TModel;
        this.AModeL = AModel;
    }


}
