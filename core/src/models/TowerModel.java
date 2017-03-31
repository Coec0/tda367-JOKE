package models;

import com.badlogic.gdx.utils.Array;
import towers.Soldier;
import towers.Tank;
import towers.Tower;

import java.util.ArrayList;

public class TowerModel {
    Array<Tower> towers;


    public TowerModel(){
        towers = new Array<Tower>(false, 100);
    }

    public void createSoldier(){
        towers.add(new Soldier());
    }
    public void createTank(){
        towers.add(new Tank());
    }

    public void getTower(int index){
        towers.get(index);
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
