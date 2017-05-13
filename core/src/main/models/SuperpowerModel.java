package models;

import buildings.towers.Tower;
import com.badlogic.gdx.utils.Array;
import enemies.Enemy;
import superpowers.Nuke;
import superpowers.BuildWall;
import utilities.Radar;

/**
 * Created by Emil on 2017-05-04.
 */
public class SuperpowerModel {
    private Nuke nuke;


    public SuperpowerModel(){
        nuke = new Nuke();
    }

    public void useNuke(Array<Enemy> enemies){
        nuke.perform(enemies);
    }


    public void useMinutemen(int x, int y){

    }

    public void useWall(int x, int y){

    }

    public void useTowerBoost(Array<Tower> towers){

    }
}
