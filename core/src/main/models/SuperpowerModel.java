package models;

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

}
