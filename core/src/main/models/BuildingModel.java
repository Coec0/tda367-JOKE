package models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;

import buildings.Building;
import buildings.WhiteHouse;
import buildings.towers.Soldier;
import buildings.towers.Tank;
import buildings.towers.Tower;
import enemies.Alien;
import enemies.Enemy;
import utilities.BuildingObserver;
import utilities.Node;
import utilities.Radar;
import utilities.UpdateObserver;

public class BuildingModel implements UpdateObserver {
	Array<Tower> towers;
	Array<Building> buildings;
	Array<Alien> aliens;
	private Radar radar;

	public BuildingModel(Radar radar) {
		towers = new Array<Tower>(false, 100);
		buildings = new Array<Building>(false, 100);
		// aliens = AModel.getAllAliens();
		this.radar = radar;
	}

	public void createSoldier(Node pos) {
		towers.add(new Soldier((int) pos.getX(), Gdx.graphics.getHeight() - (int) pos.getY()));
		notifyObservers(towers.peek());
	}

	public void createTank(Node pos) {
		towers.add(new Tank((int) pos.getX(), Gdx.graphics.getHeight() - (int) pos.getY()));
		notifyObservers(towers.peek());
	}

	public void createWhiteHouse(Node pos) {
		buildings.add(new WhiteHouse("WhiteHouse", (int) pos.getX(), Gdx.graphics.getHeight() - (int) pos.getY()));
		notifyObservers(buildings.peek());
		;
	}

	public Tower getTower(int index) {
		return towers.get(index);
	}

	public Tower peekTower() {
		return towers.peek();
	}

	public void sellTower(int index) {
		towers.removeIndex(index);
	}

	public void upgradeTower(int index) {
		// TODO
	}

	public boolean checkIfInRadius(Tower tower, Node enemyNode) {
		float deltaX = enemyNode.getX() - tower.getPos().getX();
		float deltaY = enemyNode.getY() - tower.getPos().getY();

		if ((deltaX * deltaX) + (deltaY * deltaY) < (tower.getRadius() * tower.getRadius())) {
			return true;
		}

		return false;
	}

	private void findTargets() {
		Array<Enemy> foundAliens;
		for (Tower tower : towers) {

			foundAliens = radar.scan(tower.getPos(), tower.getRadius()); // tmp
			if (foundAliens.size > 0) {
				tower.setTarget(foundAliens);

			}
		}
	}

	@Override
	public void update(float deltaTime) {
		findTargets();
	}

	private Array<BuildingObserver> observers = new Array<BuildingObserver>(false, 10);

	public void addObserver(BuildingObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(BuildingObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers(Building building) {
		for (BuildingObserver observer : observers)
			observer.actOnBuildingChange(building);
	}

}
