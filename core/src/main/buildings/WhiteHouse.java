package buildings;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import observers.AlienObserver;
import observers.WhiteHouseObserver;
import politics.Parliament;
import politics.parties.Party;

public class WhiteHouse extends BoardObject implements AlienObserver{
	private int health=20; //temp
	private float money;
	private Parliament parliament;	
	
	public WhiteHouse(String name, int x, int y, float size, float money, Parliament parliament){
		super(name, x, y,size, 0);
		this.parliament = parliament;
		this.setMoney(money);
	}
	
	public WhiteHouse(String name, int x, int y, float size, float money){
		this(name, x, y,size, money, new Parliament());
	}
	
	/**
	 * Vote for party. Takes in party class
	 * @param party
	 */
	public <T extends Party> void voteParty(T party){
		parliament.voteParty(party);
		notifyObservers(this);
	}
	
	public Parliament getParliament(){
		return parliament;
	}
	
	public void removeHealth(int amount){
		setHealth(health-amount);
	}
	
	/**
	 * Removes one health.
	 */
	public void removeHealth(){
		setHealth(health-1);
	}
	
	public void setHealth(int amount){
		health = amount;
		System.out.println(health);
		notifyObservers(this);
	}
	
	public void addHealth(int amount){
		setHealth(health+amount);
	}
	
	/**
	 * Adds one health.
	 */
	public void addHealth(){
		setHealth(health+1);
	}
	
	public int getHealth(){
		return health;
	}

	public void addMoney(float amount){
		setMoney(money+amount);
	}
	
	public void setMoney(float amount){
		money = amount;
		notifyObservers(this);
	}
	
	public void removeMoney(float amount){
		setMoney(money-amount);
	}
	
	public float getMoney(){
		return money;
	}
	
	private Array<WhiteHouseObserver> observers = new Array<WhiteHouseObserver>(false, 10);

	public void addObserver(WhiteHouseObserver observer) {
		observers.add(observer);
	}

	public void removeObserver(WhiteHouseObserver observer) {
		observers.removeValue(observer, false);
	}

	private void notifyObservers(WhiteHouse whitehouse) {
		for (WhiteHouseObserver observer : observers)
			observer.actOnWhiteHouseChange(whitehouse);
	}

	@Override
	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void actOnEnemyChange(Enemy enemy, boolean remove) {
		if(remove)
			addMoney(enemy.getMoney());
		
	}
}
