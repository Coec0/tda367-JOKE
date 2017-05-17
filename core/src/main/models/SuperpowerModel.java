package models;

import cooldown.CooldownHandler;
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
    private Array<Tower> towers;
    private CooldownHandler cdh;

    private boolean active = false;

    public SuperpowerModel(PathFinder finder,BuildingModel BModel, AlienModel AModel, CooldownHandler cdh){
    	this.BModel = BModel;
    	this.finder = finder;
    	this.AModel = AModel;
        nuke = new Nuke();
        this.cdh = cdh;
        towerBoost = new TowerBoost(cdh);
    }

    public void useNuke(Array<Enemy> enemies){
        nuke.perform(enemies);
    }


    public void useMinutemen(){
        //active = true; //causing crash atm
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
        active = true;
        this.towers = towers;
        if(this.towers.size > 0) {
            for (Tower tower : towers) {
                towerBoost.boostTower(tower);
            }
        }
    }

    private void revertTowers(){
        if(this.towers.size > 0) {
            for (Tower t : towers) {
                towerBoost.unBoost(t);
                //reset variables to original value
            }
        }
    }

    private void checkBoost(){
        if (towerBoost.isFinished()){
            revertTowers();
            active = false;
        }
    }

    private void checkWave(){
        if (!(AModel.getWaveOn())){
            BModel.deleteMinutemen();
        }
    }


    @Override
    public void update(float deltaTime) {
        if(active){
            checkWave();
            checkBoost();
        }

    }
}
