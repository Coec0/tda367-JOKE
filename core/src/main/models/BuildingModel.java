package models;

import com.badlogic.gdx.utils.Array;

import buildings.BoardObject;
import buildings.Building;
import buildings.WhiteHouse;
import buildings.towers.Tower;
import buildings.towers.TowerUpgrader;
import enemies.Enemy;
import observers.BuildingObserver;
import observers.UpdateObserver;
import politics.parties.Party;
import utilities.Node;
import utilities.Radar;

public class BuildingModel implements UpdateObserver {
	private Array<Tower> towers;
	private Array<WhiteHouse> whitehouses;
	private Array<BoardObject> boardObjects;
	private Array<Enemy> enemies;
	private Array<Building> buildings;
	private Radar radar;
	private TowerUpgrader upgrader;
	

    public BuildingModel(Array<Enemy> enemies) {
		towers = new Array<Tower>(false, 100);
		whitehouses = new Array<WhiteHouse>(false, 4);
		boardObjects = new Array<BoardObject>(false, 40);
		buildings = new Array<Building>(false, 30);
		this.enemies = enemies;
		this.radar = new Radar();
		upgrader = new TowerUpgrader();

	}
    
    
    public Array<BoardObject> getAllBoardObjects(){
    	return boardObjects;
    }
    
    private void voteBoardObject(BoardObject BO){
    	if(BO instanceof Party){
    		whitehouses.peek().voteParty((Party)BO);
    	}
    }

    public void clickedBuilding(BoardObject building){
    	notifyObservers(building, false, true);
    }
    
	public void deselect(BoardObject building) {
		notifyObservers(building, true, true);
	}
    
	public void addBoardObject(BoardObject BO){
		if(BO instanceof Tower){
			towers.add((Tower)BO);
		} else if(BO instanceof Building){
			buildings.add((Building)BO);
		}
		boardObjects.add(BO);
		BO.setActive(true);
		voteBoardObject(BO);
		notifyObservers(BO, false, false);
	}
	
	public void addBoardObject(BoardObject BO, int x, int y){
		BO.setPos(x, y);
		this.addBoardObject(BO);
	}
	
	public void addTower(Tower tower){
		towers.add(tower);
		this.addBoardObject(tower);
	}
	
	public void addTower(Tower tower, int x, int y){
		tower.setPos(x, y);
		this.addTower(tower);
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

	public void sellBuilding(BoardObject building) {
		building.setActive(false);
		if(building instanceof Tower)
			towers.removeValue((Tower)building, false);
		notifyObservers(building, true, false);
	}

	public void upgradeTower(Tower tower) {

	}
	
	public void fireAllTowers(){
		for(Tower tower : towers){
			if(!tower.isInCooldown() && tower.hasTarget()){
				tower.shoot();
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

	private void enemiesInRadius(){
		Array<Enemy> foundAliens;
		for (Building b : buildings){
			foundAliens = radar.scan(b.getPos(), b.getRadius(), enemies);
			//TODO
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
		for (Building building: buildings){
			building.usePower();
		}
	}
	
	private void checkWhitehouses(){
		Array<Enemy> closeAliens;
		for (WhiteHouse whitehouse : whitehouses) {
			closeAliens = radar.scan(whitehouse.getPos(), 5, enemies); //5 radius for now
			if (closeAliens.size > 0) {
				for (Enemy enemy : closeAliens){
					if(!enemy.isDead()){
						whitehouse.removeHealth();
						enemy.kill();
					}
				}
			}
		}
	}

	@Override
	public void update(float deltaTime) {
		setTargets();
		fireAllTowers();
		useBuildingPowers(); // Maybe move from here
		checkWhitehouses();
		
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
