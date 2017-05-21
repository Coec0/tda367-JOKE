package waves;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;

import controllers.AlienController;
import enemies.*;

public class EnemyWavesCreator{
	
	private int enemyCounter = 0;
	private int frames;

	LevelCreator levelCreator = new LevelCreator();
	
	private Array<Level> levels;
	private Level currentLevel;
	private int currentWaveIndex;



	public EnemyWavesCreator(){
		levels = levelCreator.getAllLevels();
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
		enemyCounter = 0;
		return currentLevel.getNextWave(true);
		
		
	}
		
	private void nextLevel(){
		currentLevel = levels.pop();
	}
	/*private void spawnNextEnemy(){ 
	if(enemyCounter < wave.size){
		cont.spawnAlien(AlienFactory.createAlien()); //TODO remove hardcoded alien!
		enemyCounter++;
	}else{
		waveON = false;
	}*/
	
	
	
	/*@Override
	public boolean keyDown (int keycode) {
		if(keycode == Keys.SPACE){
			nextWave();
			waveON = true;
			return true;
		}
		return false;
	}*/
	
	/*
	@Override
	public void update(float deltaTime) {
		frames++;
		if(frames > 10){
			if(waveON){
				spawnNextEnemy();
			}
			frames = 0;
			
		}
	}
	*/
}
