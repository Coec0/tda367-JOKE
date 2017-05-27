package com.example.illegalaliens.models;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.UpdateObserver;
import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.boardobjects.BoardObjectObserver;
import com.example.illegalaliens.models.boardobjects.WhiteHouse;
import com.example.illegalaliens.models.boardobjects.buildings.Building;
import com.example.illegalaliens.models.boardobjects.buildings.RiotShield;
import com.example.illegalaliens.models.boardobjects.towers.*;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.models.politics.parties.Voter;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.Radar;
import com.example.illegalaliens.utilities.cooldown.CooldownHandler;
import com.example.illegalaliens.utilities.path.PathFinder;

public class BoardObjectModel implements UpdateObserver {
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

    public BoardObjectModel(Array<Enemy> enemies, CooldownHandler cdh, Radar radar, PathFinder finder) {
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
			if(radar.isNodeWithinRadius(new Node(x, y),sprite.getSize(),BO.getPos(), BO.getSize()))
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
		if(whitehouses.first().getMoney() >= upgrader.getDamageUpgradeCost(tower)){
			upgrader.upgradeDamage(tower);
			whitehouses.first().removeMoney(upgrader.getDamageUpgradeCost(tower));
		}
	}

	public void upgradeTowerCooldown(Tower tower) {
		if (whitehouses.first().getMoney() >= upgrader.getCooldownUpgradeCost(tower)) {
			upgrader.reduceCooldown(tower);
			whitehouses.first().removeMoney(upgrader.getCooldownUpgradeCost(tower));

		}
	}
	public void upgradeTowerRadius(Tower tower){
		if (whitehouses.first().getMoney() >= upgrader.getRadiusUpgradeCost(tower)){
			upgrader.upgradeRadius(tower);
			whitehouses.first().removeMoney(upgrader.getRadiusUpgradeCost(tower));
		}
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
				foundAliens = scanEnemies(b.getPos(),b.getRadius(),enemies);
				((RiotShield) b).updateEnemies(foundAliens);
			}

			/*if (b instanceof NetGunner){
				b = (NetGunner) b;
				foundAliens = radar.scan(b.getPos(), b.getRadius(), enemies);
				((NetGunner) b).updateEnemies(foundAliens);
			}*/

		}


	}
	
	public Array<Enemy> scanEnemies(Node pos, float radius, Array<Enemy> allEnemies){
    	Array<Enemy> foundEnemies = new Array<Enemy>();
    	for(Enemy enemy : allEnemies){
    		if(radar.isNodeWithinRadius(pos, radius, enemy.getPos(), enemy.getRadius())){
    			foundEnemies.add(enemy);
    		}
    	}
    	return foundEnemies;
    }

	private void setTargets() {
		Array<Enemy> foundAliens;
		for (Tower tower : towers) {

			foundAliens = scanEnemies(tower.getPos(), tower.getRadius(), enemies); // tmp
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

	private Array<BoardObjectObserver> observers = new Array<BoardObjectObserver>(false, 10);

	public void addObserver(BoardObjectObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(BoardObjectObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers(BoardObject boardObject, boolean remove, boolean clickedOn) {
		for (BoardObjectObserver observer : observers)
			observer.actOnBoardObjectChange(boardObject, remove, clickedOn);
	}




}
