package models;

import superpowers.TowerBoost;
import towers.Minutemen;
import towers.TowerFactory;
import com.badlogic.gdx.utils.Array;

import buildings.Wall;
import towers.Tower;
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
    private TowerBoost towerBoost;
    private Array<Minutemen> minutemenArray;

    public SuperpowerModel(PathFinder finder,BuildingModel BModel){
    	this.BModel = BModel;
    	this.finder = finder;
        nuke = new Nuke();
        towerBoost = new TowerBoost();
        minutemenArray = new Array<Minutemen>(false, 4);
    }

    public void useNuke(Array<Enemy> enemies){
        nuke.perform(enemies);
    }


    public void useMinutemen(){
        BModel.addBoardObject(TowerFactory.createMinutemen(585,495));
        BModel.addBoardObject(TowerFactory.createMinutemen(590,485));
        BModel.addBoardObject(TowerFactory.createMinutemen(595,480));
        BModel.addBoardObject(TowerFactory.createMinutemen(5600,475));
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
       for (Tower tower: towers){
           towerBoost.boostTower(tower);
       }
    }
}
