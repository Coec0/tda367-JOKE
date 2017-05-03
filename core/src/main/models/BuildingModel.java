package models;

import buildings.towers.TowerUpgrader;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import buildings.Building;
import buildings.WhiteHouse;
import buildings.towers.Tower;
import enemies.Alien;
import enemies.Enemy;
import politics.parties.Party;
import utilities.BuildingObserver;
import utilities.Node;
import utilities.Radar;
import utilities.UpdateObserver;

public class BuildingModel implements UpdateObserver {
	Array<Tower> towers;
	Array<WhiteHouse> whitehouses;
	Array<Alien> aliens;
	private Radar radar;
	private TowerUpgrader upgrader;

    public BuildingModel(Radar radar) {
		towers = new Array<Tower>(false, 100);
		whitehouses = new Array<WhiteHouse>(false, 4);
		// aliens = AModel.getAllAliens();
		this.radar = radar;
		upgrader = new TowerUpgrader();
	}
    
    private void voteTower(Tower tower){
    	if(tower instanceof Party){
    		whitehouses.peek().voteParty((Party)tower);
    	}
    }

	public void addTower(Tower tower){
		towers.add(tower);
		voteTower(tower);
		notifyObservers(towers.peek(), false);
	}
	
	public void addTower(Tower tower, int x, int y){
		tower.setPos(x, y);
		this.addTower(tower);
	}

	public void createWhiteHouse(int x, int y) {
		whitehouses.add(new WhiteHouse("WhiteHouse", x, Gdx.graphics.getHeight() - y));
		notifyObservers(whitehouses.peek(), false);
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

	public void sellTower(int index) {
		towers.removeIndex(index);
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

			foundAliens = radar.scan(tower.getPos(), tower.getRadius()); // tmp
			tower.setTarget(foundAliens);
		}
		
	}
	
	private void checkWhitehouses(){
		Array<Enemy> closeAliens;
		for (WhiteHouse whitehouse : whitehouses) {
			closeAliens = radar.scan(whitehouse.getPos(), 5); //5 radius for now
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

	private void notifyObservers(Building building, boolean remove) {
		for (BuildingObserver observer : observers)
			observer.actOnBuildingChange(building, remove);
	}


}
