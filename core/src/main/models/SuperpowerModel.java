package models;

import com.badlogic.gdx.utils.Array;

import buildings.BoardObject;
import buildings.Wall;
import buildings.WhiteHouse;
import cooldown.CooldownHandler;
import enemies.Enemy;
import observers.SuperpowerObserver;
import observers.UpdateObserver;
import path.PathFinder;
import path.RoadSection;
import politics.parties.Party;
import politics.parties.PartyFactory;
import superpowers.Nuke;
import superpowers.Superpower;
import superpowers.TowerBoost;
import towers.BOPrototypes;
import towers.Minutemen;
import towers.Tower;
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
    private Wall wall;
    private Minutemen minuteman;
    private boolean boostActive = false;
    private boolean minutemenActive = false;
    private WhiteHouse whitehouse;

    public SuperpowerModel(PathFinder finder,BuildingModel BModel, AlienModel AModel, CooldownHandler cdh){
    	this.BModel = BModel;
    	this.finder = finder;
    	this.AModel = AModel;
        this.cdh = cdh;
        
        minuteman = new Minutemen(0, 0, 400);
        wall = new Wall("WALL", 0, 0, 15, 200);
        nuke = new Nuke(500);
        towerBoost = new TowerBoost(cdh, 300);
        this.whitehouse = BModel.getWhiteHouses().peek();
    }

    
    private void notifySuperPowers() {
		notifyObservers(nuke);
		notifyObservers(towerBoost);
		notifyObservers(wall);
		notifyObservers(minuteman);
	}

    /**
     * Kills all enemies on the map if the user has enough republican points
     * @param enemies all enemies in the map
     */
    public void useNuke(Array<Enemy> enemies){
    	if(whitehouse.getParty(PartyFactory.Democrat(0)).getPoints()>nuke.getSuperPowerCost()){
    		nuke.perform(enemies);
    		removePoints(PartyFactory.Democrat(0, nuke.getSuperPowerCost()));
    	}
    }

    /**
     * Creates and adds 4 minutemen via BuildingModel with help from prototypes for them
     * if republican points are above 200
     * @param prototypes to help create the minutemen
     */
    public void useMinutemen(BOPrototypes prototypes){
    	if(whitehouse.getParty(PartyFactory.Democrat(0)).getPoints()>minuteman.getSuperPowerCost()){
    		minutemenActive = true;
    		BModel.addBoardObject(minuteman.clone(600, 495));
    		BModel.addBoardObject(minuteman.clone(550, 485));
    		BModel.addBoardObject(minuteman.clone(650, 485));
    		BModel.addBoardObject(minuteman.clone(500, 485));
    		removePoints(PartyFactory.Democrat(0, minuteman.getSuperPowerCost()));
    	}
    }

    /**
     * Creates a wall the user is able to place on roads on the map
     * if democrat points are above 200
     * @param x position
     * @param y position
     */

    public void useWall(int x, int y){
    	if(whitehouse.getParty(PartyFactory.Republican(0)).getPoints()>wall.getSuperPowerCost() && !AModel.getWaveAlive()){
    		
    		
    		Node node = new Node(x,y);
    		RoadSection rs = finder.findRoadSection(node);
    	
    		if(finder.canRemoveNeighbors(rs)){
    			Wall wall = ((Wall)BModel.getHighlighted());
    			wall.setPos(node);
    			wall.rotateTowards(rs.getStart());
    			BModel.purchaseHighlightedObject(x, y);
    			finder.removeNeighbor(rs);
    			finder.calculateAllShortest();
    			this.wall.setSuperPowerCost((int)(this.wall.getSuperPowerCost()* 1.2f));
    			notifyObservers(this.wall);
    			removePoints(PartyFactory.Republican(0, wall.getSuperPowerCost()));
    		}
    	}
    }

    /**
     * Boosts all towers on the map for a short duration, if democrat points are above 200.
     * @param towers the towers to boost
     */

    public void useTowerBoost(Array<Tower> towers){
    	if(whitehouse.getParty(PartyFactory.Republican(0)).getPoints()>towerBoost.getSuperPowerCost()){
    		boostActive = true;
    		this.towers = towers;
    		if(this.towers.size > 0) {
    			for (Tower tower : towers) {
    				towerBoost.boostTower(tower);
    			}
    		}
    		removePoints(PartyFactory.Republican(0, towerBoost.getSuperPowerCost()));
        }
    }

    private void removePoints(Party party){
    	whitehouse.removePointsParty(party);
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

    
	private Array<SuperpowerObserver> observers = new Array<SuperpowerObserver>(false, 10);

	public void addObserver(SuperpowerObserver observer) {
		observers.add(observer);
		notifySuperPowers();
	}


	public void removeObserver(SuperpowerObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers(Superpower superpower) {
		for (SuperpowerObserver observer : observers)
			observer.actOnSuperPowerChange(superpower);
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


	public BoardObject getWall(int x, int y) {
		return wall.clone(x, y);
	}

}
