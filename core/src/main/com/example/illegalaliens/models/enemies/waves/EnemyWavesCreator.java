package com.example.illegalaliens.models.enemies.waves;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.controllers.AlienController;
import com.example.illegalaliens.models.enemies.*;
import com.example.illegalaliens.models.enemies.levels.Level;
import com.example.illegalaliens.models.enemies.levels.LevelCreator;

public class EnemyWavesCreator{
	
	private int levelIndex = 0;
	private LevelCreator levelCreator = new LevelCreator();
	private Level currentLevel;
	private int currentWaveIndex;



	public EnemyWavesCreator(){
		nextLevel();
		currentWaveIndex = 0;
	}
	
	public boolean hasLevelRandomSpawn(){
		return currentLevel.isRandomSpawn();
	}
	
	public Array<Enemy> getNextWave(){
		currentWaveIndex++;
		if(currentWaveIndex >= currentLevel.getWavesAmount()){
			nextLevel();
			currentWaveIndex = 0;
		}
		return currentLevel.getNextWave(true);
		
		
	}
		
	private void nextLevel(){
		levelIndex++;
		currentLevel = levelCreator.getLevel(levelIndex);
	}
	
}
