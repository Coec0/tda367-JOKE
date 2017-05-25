package waves;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;

import controllers.AlienController;
import enemies.*;

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
