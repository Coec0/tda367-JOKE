package com.example.illegalaliens.models.enemies.levels;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;

public class Level {
	
	private  Array<LevelHelperObject> spawnableEnemies;
	private int wavesAmount;
	private int currentWave = 0;
	private boolean randomSpawn;
	
	public Level(int wavesAmount,Array<LevelHelperObject> spawnableEnemies,boolean randomSpawn){
		this.randomSpawn = randomSpawn;
		this.spawnableEnemies = spawnableEnemies;
		this.wavesAmount = wavesAmount;
	}
	
	public boolean isRandomSpawn(){
		return randomSpawn;
	}
	
	public Array<Enemy> getNextWave(boolean shuffle){
		Array<Enemy> wave = new Array<Enemy>();
		currentWave++;
		for(LevelHelperObject WHO : spawnableEnemies){
			WHO.setAmount(WHO.getAmount() * currentWave);
			for(int i = 0; i < WHO.getAmount(); i++){
				wave.add(WHO.getEnemy());
			}
		}
		
		if(shuffle){
			wave.shuffle();
		}
		
		return wave;
	}
	
	
	public int getWavesAmount(){
		return wavesAmount;
	}
	
	
	public Array<LevelHelperObject> getSpawnableEnemies(){
		return spawnableEnemies;
	}
	
}
