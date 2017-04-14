package utilities;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;

public class Level {
	
	private  Array<Enemy> spawnableEnemies;
	private int difficulty; 
	private int wavesAmount;
	
	public Level(int difficulty,int wavesAmount,Array<Enemy> spawnableEnemies){
		this.spawnableEnemies = spawnableEnemies;
		this.wavesAmount = wavesAmount;
		this.difficulty = difficulty;
	}
	
	public int getDifficulty(){
		return difficulty;
	}
	
	public int getWavesAmount(){
		return wavesAmount;
	}
	
	public void setDifficulty(int difficulty){
		this.difficulty = difficulty;
	}
	
	public Array<Enemy> getSpawnableEnemies(){
		return spawnableEnemies;
	}
	
}
