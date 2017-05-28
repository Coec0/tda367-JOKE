package com.example.illegalaliens.models.enemies.levels;

import com.example.illegalaliens.models.enemies.Enemy;

public class LevelHelperObject {
	private Enemy enemy;
	private int amount;	
	
	public LevelHelperObject(Enemy enemy, int amount){
		this.enemy = enemy;
		this.amount = amount;
	}
	
	public int getAmount(){
		return amount;
	}
	
	public void setAmount(int amount){
		this.amount = amount;
	}
	
	public Enemy getEnemy(){
		return enemy;
	}
}
