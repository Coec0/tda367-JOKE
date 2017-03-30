package controllers;

import models.TowerModel;
import views.TowerView;

public class TowerController {
    TowerView view;
    TowerModel model;

    public TowerController(){
        view = new TowerView();
        model = new TowerModel();
    }


}
