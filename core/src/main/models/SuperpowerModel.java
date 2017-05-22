package models;

import com.badlogic.gdx.utils.Array;

import buildings.Wall;
import buildings.WhiteHouse;
import cooldown.CooldownHandler;
import enemies.Enemy;
import observers.UpdateObserver;
import path.PathFinder;
import path.RoadSection;
import politics.parties.PartyFactory;
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
    private WhiteHouse whitehouse;

    public SuperpowerModel(PathFinder finder,BuildingModel BModel, AlienModel AModel, CooldownHandler cdh){
    	this.BModel = BModel;
    	this.finder = finder;
    	this.AModel = AModel;
        nuke = new Nuke();
        this.cdh = cdh;
        towerBoost = new TowerBoost(cdh);
        this.whitehouse = BModel.getWhiteHouses().peek();
    }


    /**
     * Kills all enemies on the map if the user has enough republican points
     * @param enemies all enemies in the map
     */
    public void useNuke(Array<Enemy> enemies){
    	if(whitehouse.getParty(PartyFactory.Republican(0)).getPoints()>200){
    		nuke.perform(enemies);
    		whitehouse.removePointsParty(PartyFactory.Republican(0, 200));
    	}
    }

    /**
     * Creates and adds 4 minutemen via BuildingModel with help from prototypes for them
     * if republican points are above 200
     * @param prototypes to help create the minutemen
     */
    public void useMinutemen(BOPrototypes prototypes){
    	if(whitehouse.getParty(PartyFactory.Republican(0)).getPoints()>200){
    		minutemenActive = true;
    		BModel.addBoardObject(TowerFactory.createMinutemen(prototypes,600,495));
    		BModel.addBoardObject(TowerFactory.createMinutemen(prototypes,550,485));
    		BModel.addBoardObject(TowerFactory.createMinutemen(prototypes,650,485));
    		BModel.addBoardObject(TowerFactory.createMinutemen(prototypes,500,485));
    		whitehouse.removePointsParty(PartyFactory.Republican(0, 200));
    	}
    }

    /**
     * Creates a wall the user is able to place on roads on the map
     * if democrat points are above 200
     * @param x position
     * @param y position
     */

    public void useWall(int x, int y){
    	if(whitehouse.getParty(PartyFactory.Democrat(0)).getPoints()>200){
    		
    		
    		Node node = new Node(x,y);
    		RoadSection rs = finder.findRoadSection(node);
    	
    		if(finder.canRemoveNeighbors(rs)){
    			Wall wall = ((Wall)BModel.getHighlighted());
    			wall.setPos(node);
    			wall.rotateTowards(rs.getStart());
    			BModel.purchaseHighlightedObject(x, y);
    			finder.removeNeighbor(rs);
    			finder.calculateAllShortest();
    			whitehouse.removePointsParty(PartyFactory.Democrat(0, 200));
    		}
    	}
    }

    /**
     * Boosts all towers on the map for a short duration, if democrat points are above 200.
     * @param towers the towers to boost
     */

    public void useTowerBoost(Array<Tower> towers){
    	if(whitehouse.getParty(PartyFactory.Democrat(0)).getPoints()>200){
    		boostActive = true;
    		this.towers = towers;
    		if(this.towers.size > 0) {
    			for (Tower tower : towers) {
    				towerBoost.boostTower(tower);
    			}
    		}
    		whitehouse.removePointsParty(PartyFactory.Democrat(0, 200));
        }
    }


    /**
     * Reverts all towers that was previously boosted by resetting variables to previous value
     */
    private void revertTowers(){
        if(this.towers.size > 0) {
            for (Tower t : towers) {
                towerBoost.unBoost(t);
                //reset variables to original value
            }
        }
    }

    /**
     * Method runs every frame in the update method to see if the towers should be reverted or not
     */
    private void checkBoost(){
        if (towerBoost.isFinished()){
            revertTowers();
            boostActive = false;
        }
    }

    /**
     * Method runs every frame in the update method to see if the minutemen should be removed or not
     */

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
