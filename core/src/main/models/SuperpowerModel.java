package models;

import observers.UpdateObserver;
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
public class SuperpowerModel implements UpdateObserver {
    private Nuke nuke;
    private PathFinder finder;
    private BuildingModel BModel;
    private AlienModel AModel; //buildingmodel and alienmodel should be moved
    private TowerBoost towerBoost;
    private Array<Tower> boostedTowers;

    public SuperpowerModel(PathFinder finder,BuildingModel BModel, AlienModel AModel){
    	this.BModel = BModel;
    	this.finder = finder;
    	this.AModel = AModel;
        nuke = new Nuke();
        towerBoost = new TowerBoost();
        boostedTowers = new Array<Tower>(false, 100);
    }

    public void useNuke(Array<Enemy> enemies){
        nuke.perform(enemies);
    }


    public void useMinutemen(){
        BModel.addBoardObject(TowerFactory.createMinutemen(585,495));
        BModel.addBoardObject(TowerFactory.createMinutemen(590,485));
        BModel.addBoardObject(TowerFactory.createMinutemen(595,480));
        BModel.addBoardObject(TowerFactory.createMinutemen(560,475));
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
        boostedTowers = towers;
       for (Tower tower: towers){
           towerBoost.boostTower(tower);
       }
    }

    private void revertTowers(){
        for (Tower bt: boostedTowers){
            //reset variables to original value
        }
    }

    private void checkBoost(){
        if (towerBoost.isFinished()){
            revertTowers();
        }
    }

    private void checkWave(){
        if (!(AModel.getWaveOn())){
            BModel.deleteMinutemen();
        }
    }

    @Override
    public void update(float deltaTime) {
      checkWave();
      checkBoost();
    }
}
