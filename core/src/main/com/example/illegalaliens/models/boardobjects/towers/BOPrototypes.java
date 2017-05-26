package com.example.illegalaliens.models.boardobjects.towers;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.boardobjects.buildings.Building;
import com.example.illegalaliens.models.boardobjects.buildings.RiotShield;

public class BOPrototypes {
	private Tower ranger, soldier, sniper, tank, minutemen, netGunner;
	private Building riotShield;
	private Array<Tower> towers;
	private Array<Building> buildings;
	public BOPrototypes(){
		reset();
	}
	
	public Tank getTank(int x, int y){
		return (Tank)tank.clone(x, y);
	}
	
	public Soldier getSoldier(int x, int y){
		return (Soldier)soldier.clone(x, y);
	}
	
	public Sniper getSniper(int x, int y){
		return (Sniper)sniper.clone(x, y);
	}
	
	public Ranger getRanger(int x, int y){
		return (Ranger)ranger.clone(x, y);
	}
	
	public RiotShield getRiotShield(int x, int y){
		return (RiotShield)riotShield.clone(x, y);
	}
	
	public NetGunner getNetGunner(int x, int y){
		return (NetGunner)netGunner.clone(x, y);
	}

	public Minutemen getMinutemen(int x, int y){return (Minutemen)minutemen.clone(x,y);}
	
	
	/**
	 * Changes damage.
	 * @param percent
	 * Multiplies current damage with this. 1.5 will increase damage with 50%.
	 */
	public void changeDamage(float percent){
		for(Tower tower: towers){
			tower.setDamage(tower.getDamage()*percent);
		}
		notifyObservers();
	}
	
	/**
	 * Revert damage change. "changeDamage(float percent) should be run first."
	 * @param percent
	 * Divides current damage with this. 1.5 will revert a 50% increase to 0%.
	 */
	public void revertDamage(float percent){
		for(Tower tower: towers){
			tower.setDamage(tower.getDamage()/percent);
		}
		notifyObservers();
	}
	
	/**
	 * Changes cost.
	 * @param percent
	 * Multiplies current cost with this. 1.5 will increase cost with 50%.
	 */
	public void changeCost(float percent){
		for(Tower tower: towers){
			tower.setCost(tower.getCost()*percent);
		}
		for(Building building: buildings){
			building.setCost(building.getCost()*percent);
		}
		notifyObservers();
	}
	
	/**
	 * Reverts cost.
	 * @param percent
	 * Divides current cost with this. 1.5 will revert a 50% increase to 0%.
	 */
	public void revertCost(float percent){
		for(Tower tower: towers){
			tower.setCost(tower.getCost()/percent);
		}
		for(Building building: buildings){
			building.setCost(building.getCost()/percent);
		}
		notifyObservers();
	}
	
	public void reset(){
		towers = new Array<Tower>(false, 4);
		buildings = new Array<Building>(false, 2);
		ranger = new Ranger(0, 0);
		soldier = new Soldier(0, 0);
		sniper = new Sniper(0, 0);
		tank = new Tank(0, 0);
		minutemen = new Minutemen(0,0, 200);
		netGunner = new NetGunner(0,0);
		
		towers.add(ranger);
		towers.add(soldier);
		towers.add(sniper);
		towers.add(tank);
		towers.add(minutemen);
		towers.add(netGunner);
		
		riotShield = new RiotShield(0,0);
		buildings.add(riotShield);
		
		notifyObservers();
	}
	
	
	private Array<PrototypeObserver> observers = new Array<PrototypeObserver>(false, 10);

	public void addObserver(PrototypeObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(PrototypeObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers() {
		for (PrototypeObserver observer : observers)
			observer.actOnPrototypeChange(this);
	}
	
}
