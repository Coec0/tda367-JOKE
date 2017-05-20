package models;

import com.badlogic.gdx.utils.Array;

import buildings.Wall;
import cooldown.CooldownHandler;
import enemies.Enemy;
import observers.UpdateObserver;
import path.PathFinder;
import path.RoadSection;
import superpowers.Nuke;
import superpowers.TowerBoost;
import towers.BOPrototypes;
import towers.Tower;
import towers.TowerFactory;
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

    private boolean boostActive = false;
    private boolean minutemenActive = false;

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


    public void useMinutemen(BOPrototypes prototypes){
        minutemenActive = true;
        BModel.addBoardObject(TowerFactory.createMinutemen(prototypes,600,495));
        BModel.addBoardObject(TowerFactory.createMinutemen(prototypes,550,485));
        BModel.addBoardObject(TowerFactory.createMinutemen(prototypes,650,485));
        BModel.addBoardObject(TowerFactory.createMinutemen(prototypes,500,485));
    }

    public void useWall(int x, int y){
    	Node node = new Node(x,y);
    	RoadSection rs = finder.findRoadSection(node);
    	
    	if(finder.canRemoveNeighbors(rs)){
    		Wall wall = ((Wall)BModel.getHighlighted());
    		wall.setPos(node);
    		wall.rotateTowards(rs.getStart());
    		BModel.purchaseHighlightedObject(x, y);
    		finder.removeNeighbor(rs);
    		finder.calculateAllShortest();
    	}

    }

    public void useTowerBoost(Array<Tower> towers){
        boostActive = true;
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
            boostActive = false;
        }
    }

    private void checkWave(){
        if (AModel.getAllEnemies().size < 1 && (!AModel.getWaveAlive())){
            BModel.deleteMinutemen();
            minutemenActive = false;
        }
    }


    @Override
    public void update(float deltaTime) {
        if(minutemenActive){
            checkWave();

        }

        if(boostActive){
            checkBoost();
        }

    }
}
