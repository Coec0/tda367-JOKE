package models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import buildings.Building;
import buildings.WhiteHouse;
import buildings.towers.Tower;
import buildings.towers.TowerUpgrader;
import enemies.Alien;
import enemies.Enemy;
import observers.BuildingObserver;
import observers.UpdateObserver;
import politics.parties.Party;
import utilities.Node;
import utilities.Radar;

public class BuildingModel implements UpdateObserver {
	private Array<Tower> towers;
	private Array<WhiteHouse> whitehouses;
	private Array<Alien> aliens;
	private Radar radar;
	private TowerUpgrader upgrader;
	private AlienModel aModel;
	

    public BuildingModel(AlienModel aModel) {
		towers = new Array<Tower>(false, 100);
		whitehouses = new Array<WhiteHouse>(false, 4);
		this.aModel = aModel;
		// aliens = AModel.getAllAliens();
		this.radar = new Radar();
		upgrader = new TowerUpgrader();
	}
    
    
    public Array<Building> getAllBuildings(){
    	Array<Building> allBuildings = new Array<Building>();
    	for(Tower tower : towers){
    		allBuildings.add(tower);
    	}
    	for(WhiteHouse wh : whitehouses){
    		allBuildings.add(wh);
    	}
    	return allBuildings;
    }
    
    private void voteTower(Tower tower){
    	if(tower instanceof Party){
    		whitehouses.peek().voteParty((Party)tower);
    	}
    }

    public void clickedBuilding(Building building){
    	notifyObservers(building, false, true);
    }
    
	public void deselect(Building building) {
		notifyObservers(building, true, true);
	}
    
	public void addTower(Tower tower){
		towers.add(tower);
		tower.setActive(true);
		voteTower(tower);
		notifyObservers(tower, false, false);
	}
	
	public void addTower(Tower tower, int x, int y){
		tower.setPos(x, y);
		this.addTower(tower);
	}

	public void createWhiteHouse(int x, int y) {
		whitehouses.add(new WhiteHouse("WhiteHouse", x, Gdx.graphics.getHeight() - y,100)); //just tmp size for WH
		aModel.addObserver(whitehouses.peek());
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

	public void sellBuilding(Building building) {
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

	private void setTargets() {
		Array<Enemy> foundAliens;
		for (Tower tower : towers) {

			foundAliens = radar.scan(tower.getPos(), tower.getRadius(), aModel.getAllAliens()); // tmp
			tower.setTarget(foundAliens);
		}
		
	}
	
	private void checkWhitehouses(){
		Array<Enemy> closeAliens;
		for (WhiteHouse whitehouse : whitehouses) {
			closeAliens = radar.scan(whitehouse.getPos(), 5,aModel.getAllAliens()); //5 radius for now
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
		checkWhitehouses();
		
	}

	private Array<BuildingObserver> observers = new Array<BuildingObserver>(false, 10);

	public void addObserver(BuildingObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(BuildingObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers(Building building, boolean remove, boolean clickedOn) {
		for (BuildingObserver observer : observers)
			observer.actOnBuildingChange(building, remove, clickedOn);
	}




}
