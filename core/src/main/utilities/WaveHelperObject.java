package utilities;

import enemies.Enemy;

public class WaveHelperObject {
	private Enemy enemy;
	private int amount;	//amount of enemy to spawn
	
	public WaveHelperObject(Enemy enemy, int amount){
		this.enemy = enemy;
		this.amount = amount;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public Enemy getEnemy(){
		return enemy;
	}
}
