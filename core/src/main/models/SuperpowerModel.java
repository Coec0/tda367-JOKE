package models;

import com.badlogic.gdx.utils.Array;

import buildings.Wall;
import buildings.towers.Tower;
import enemies.Enemy;
import path.PathFinder;
import path.RoadSection;
import superpowers.Nuke;
import utilities.Node;

/**
 * Created by Emil on 2017-05-04.
 */
public class SuperpowerModel {
    private Nuke nuke;
    private PathFinder finder;
    private BuildingModel BModel;
    


    public SuperpowerModel(PathFinder finder,BuildingModel BModel){
    	this.BModel = BModel;
    	this.finder = finder;
        nuke = new Nuke();
    }

    public void useNuke(Array<Enemy> enemies){
        nuke.perform(enemies);
    }


    public void useMinutemen(int x, int y){

    }

    public void useWall(int x, int y){
    	Node node = new Node(x,y);
    	RoadSection rs = finder.findRoadSection(node);
    	
    	if(finder.canRemoveNeighbors(rs)){
    		Wall wall = new Wall("Trump's wall",x,y, 10);
    		wall.rotateTowards(rs.getStart());
    		BModel.addBoardObject(wall);
    		finder.removeNeighbor(rs);
    		finder.calculateAllShortest();
    	}
    	
    }

    public void useTowerBoost(Array<Tower> towers){

    }
}
