package towers;

import com.badlogic.gdx.utils.Array;

import buildings.Building;
import enemies.Enemy;
import observers.AlienObserver;
import observers.PrototypeObserver;

public class BOPrototypes {
	Tower ranger, soldier, sniper, tank;
	Building riotShield, netGunner;
	Array<Tower> towers;
	Array<Building> buildings;
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
	 * Changes cost.
	 * @param percent
	 * Multiplies current cost with this. 1.5 will increase cost with 50%.
	 */
	public void changeCost(float percent){
		for(Tower tower: towers){
			tower.setCost((int)(((float)tower.getCost())*percent));
		}
		for(Building building: buildings){
			building.setCost((int)(((float)building.getCost())*percent));
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
		towers.add(ranger);
		towers.add(soldier);
		towers.add(sniper);
		towers.add(tank);
		
		riotShield = new RiotShield(0,0);
		netGunner = new NetGunner(0,0);
		buildings.add(riotShield);
		buildings.add(netGunner);
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
