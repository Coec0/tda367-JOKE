package models;

import com.badlogic.gdx.utils.Array;
import controllers.TowerController;
import towers.Soldier;
import towers.Tank;
import towers.Tower;
import utilities.Node;
import utilities.SpriteAdapter;

import java.util.ArrayList;

public class TowerModel {
    Array<Tower> towers;


    public TowerModel(AlienModel AModel){
        towers = new Array<Tower>(false, 100);
        AModel = new AlienModel();
    }

    public void createSoldier(Node pos){
        towers.add(new Soldier(new SpriteAdapter(0,0)));
    }
    public void createTank(Node pos){
        towers.add(new Tank(new SpriteAdapter(5,5)));
    }

    public Tower getTower(int index){
        return towers.get(index);
    }

    public void sellTower(int index){
        towers.removeIndex(index);
    }


    public void upgradeTower(int index){
        //TODO
    }

    public boolean checkIfInRadius(){
        return (5 < towers.get(0).getRadius());
    }

    public void target(){
        if(checkIfInRadius()){
            shoot();
        }
    }

    public void shoot(){

    }

    public float calcDistance(){
        return 5;
    }

    public void rotate(){

    }
}
