package models;

import com.badlogic.gdx.utils.Array;

import buildings.BoardObject;
import buildings.Building;
import buildings.WhiteHouse;
import towers.*;
import cooldown.CooldownHandler;
import enemies.Enemy;
import observers.BuildingObserver;
import observers.UpdateObserver;
import path.PathFinder;
import politics.parties.Voter;
import utilities.Node;
import utilities.Radar;

public class BuildingModel implements UpdateObserver {
	private Array<Tower> towers;
	private Array<WhiteHouse> whitehouses;
	private Array<BoardObject> boardObjects;
	private Array<Enemy> enemies;
	private Array<Building> buildings;
	private BoardObject highLighted;
	private Radar radar;
	private TowerUpgrader upgrader;
	private CooldownHandler cdh;
	private PathFinder finder;

    public BuildingModel(Array<Enemy> enemies, CooldownHandler cdh, Radar radar, PathFinder finder) {
		towers = new Array<Tower>(false, 100);
		whitehouses = new Array<WhiteHouse>(false, 4);
		boardObjects = new Array<BoardObject>(false, 40);
		buildings = new Array<Building>(false, 30);
		this.enemies = enemies;
		this.radar = radar;
        this.finder = finder;
		upgrader = new TowerUpgrader();
		this.cdh = cdh;	
	}
    
	public boolean isFreeSpace(int x, int y, BoardObject sprite){
		if(finder.isOnRoad(new Node(x, y), sprite.getSize()))
			return false;
		for(BoardObject BO : getAllBoardObjects()){
			if(radar.isNodeWithinRadius(new Node(x, y), BO.getPos(), sprite.getSize(), BO.getSize()))
				return false;
		}
		return true;		
	}
    
    public Array<BoardObject> getAllBoardObjects(){
    	return boardObjects;
    }
    
    private void voteBoardObject(BoardObject BO){
    	if(BO instanceof Voter){
    		Voter temp = (Voter)BO;
    		whitehouses.peek().voteParty(temp.getParty());
    	}
    }

    /*public void clickedBuyBuilding(BoardObject building){
    	onMouse = building;
    	clickedBuilding(building);
    }*/
    
    /**
     * Sells object if placed. Removes from mouse if not placed
     */
    public void trash(){
    	if(highLighted!=null && !highLighted.isActive()){
			deselectHighlighted();
		} else {
			sellBoardObject(highLighted, true);
		}
    	highLighted = null;	
    }
    
    public void purchaseHighlightedObject(int x, int y){
    	if(highLighted.getCost()<= getWhiteHouses().first().getMoney()){
			addBoardObject(highLighted, x, y);
			highLighted = null;
		}
    }
    
    public BoardObject getHighlighted(){
    	return highLighted;
    }
    
    public void clickedBuilding(BoardObject building){
    	highLighted = building;
    	notifyObservers(building, false, true);
    }
    
	public void deselectHighlighted() {
		if(highLighted != null)
			notifyObservers(highLighted, true, true);
    	highLighted = null;	
	}
    
	public void addBoardObject(BoardObject BO){
		if(BO instanceof Tower){
			towers.add((Tower)BO);
			cdh.addCooldownObject(((Tower)BO).getCooldownObject());
		} else if(BO instanceof Building){
			buildings.add((Building)BO);
			cdh.addCooldownObject(((Building)BO).getCooldownObject());
		}
		whitehouses.first().removeMoney(BO.getCost());
		boardObjects.add(BO);
		BO.setActive(true);
		voteBoardObject(BO);
		notifyObservers(BO, false, false);
	}
	
	public void addBoardObject(BoardObject BO, int x, int y){
		BO.setPos(x, y);
		this.addBoardObject(BO);
	}
	
	public void addWhiteHouse(WhiteHouse whitehouse) {
		whitehouses.add(whitehouse); //just tmp size for WH;
		notifyObservers(whitehouses.peek(), false, false);
	}
	
	public Array<WhiteHouse> getWhiteHouses(){
		return whitehouses;
	}
	public Tower getTower(int index) {
		return towers.get(index);
	}

	public Array<Tower> getTowers(){
		return towers;
	}

	public Tower peekTower() {
		return towers.peek();
	}

	public void deleteMinutemen(){
		Array<BoardObject> gonnaSell = new Array<BoardObject>(false, 4);
		for (Tower t : towers){
			if (t instanceof Minutemen)
				gonnaSell.add(t);
		}
		for(BoardObject BO : gonnaSell){
			sellBoardObject(BO, false);
		}
	}

	public Array<Minutemen> getMinutemen(){
	    Array<Minutemen> mm = new Array<Minutemen>();
	    for (Tower t: towers){
	        if (t instanceof Minutemen){
	            mm.add((Minutemen)t);
            }
        }
        return mm;
    }

	public void sellBoardObject(BoardObject BO, boolean getMoney) {
		BO.setActive(false);
		if(BO instanceof Tower){
			towers.removeValue((Tower)BO, false);
			System.out.println("deleting");
		} else if(BO instanceof Building){
			buildings.removeValue((Building)BO, false);
		}
		boardObjects.removeValue(BO, false);
		if(getMoney)
			whitehouses.peek().addMoney(BO.getValue());
		if(BO.getParty() != null && BO.getParty().getVotes() > 0)
			whitehouses.peek().removeVoteParty(BO.getParty());
		notifyObservers(BO, true, false);
	}

	public void upgradeTowerDamage(Tower tower) {
		upgrader.upgradeDamage(tower, tower.getDamage() * 1.1f);
	}

	public void upgradeTowerCooldown(Tower tower){
		upgrader.reduceCooldown(tower, tower.getCooldownObject().getCooldownTime() * 0.8f);
	}

	public void upgradeTowerRadius(Tower tower){
		upgrader.upgradeRadius(tower, tower.getRadius() * 1.1f);
	}
	
	public void fireAllTowers(){
		for(Tower tower : towers){
			if(!tower.getCooldownObject().isOnCooldown() && tower.hasTarget()){
				tower.shoot();
				tower.getCooldownObject().setOnCooldown(true);
			}
		}
	}

	public boolean checkIfInRadius(Tower tower, Node enemyNode) {
		float deltaX = enemyNode.getX() - tower.getPos().getX();
		float deltaY = enemyNode.getY() - tower.getPos().getY();

		if ((deltaX * deltaX) + (deltaY * deltaY) < (tower.getRadius() * tower.getRadius())) {
			return true;
		}

		return false;
	}

	private void updateBuildings(){
		Array<Enemy> foundAliens;
		for (Building b : buildings){
			if (b instanceof RiotShield){
				b = (RiotShield )b;
				foundAliens = radar.scan(b.getPos(),b.getRadius(),enemies);
				((RiotShield) b).updateEnemies(foundAliens);
			}

			if (b instanceof NetGunner){
				b = (NetGunner) b;
				foundAliens = radar.scan(b.getPos(), b.getRadius(), enemies);
				((NetGunner) b).updateEnemies(foundAliens);
			}

		}


	}

	private void setTargets() {
		Array<Enemy> foundAliens;
		for (Tower tower : towers) {

			foundAliens = radar.scan(tower.getPos(), tower.getRadius(), enemies); // tmp
			tower.setTarget(foundAliens);
		}
		
	}

	private void useBuildingPowers(){
		for (Building building: buildings) {
			if(!building.getCooldownObject().isOnCooldown())
				building.usePower();
		}
	}

	@Override
	public void update(float deltaTime) {
		setTargets();
		fireAllTowers();
		updateBuildings();
		useBuildingPowers(); // Maybe move from here
	}

	private Array<BuildingObserver> observers = new Array<BuildingObserver>(false, 10);

	public void addObserver(BuildingObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(BuildingObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers(BoardObject building, boolean remove, boolean clickedOn) {
		for (BuildingObserver observer : observers)
			observer.actOnBuildingChange(building, remove, clickedOn);
	}




}
