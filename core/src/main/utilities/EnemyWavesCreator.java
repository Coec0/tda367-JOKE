package utilities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;

import controllers.AlienController;
import enemies.Alien;
import enemies.AlienFactory;
import enemies.Enemy;

public class EnemyWavesCreator extends InputAdapter implements UpdateObserver  {
	

	private AlienController cont;
	private int enemyCounter = 0;
	private int frames;
	
	private Array<Level> levels = new Array<Level>();
	
	private Level currentLevel;
	private int currentWaveIndex;
	private boolean waveON;
	
	
	private Array<Enemy> wave;
	
	
	
	public EnemyWavesCreator(AlienController cont){
		this.cont = cont;
		Array<LevelHelperObject> tmpEnemies = new Array<LevelHelperObject>();
		tmpEnemies.add(new LevelHelperObject(new Alien(),5));
		levels.add(new Level(5,tmpEnemies)); //levels has to be hardcoded. Maybe in textfile in future?
		levels.add(new Level(7,tmpEnemies));
		nextLevel();
		currentWaveIndex = 0;
	}
	
	
	private void spawnNextEnemy(){ 
		if(enemyCounter < wave.size){
			cont.spawnAlien(AlienFactory.createAlien()); //TODO remove hardcoded alien!
			enemyCounter++;
		}else{
			waveON = false;
		}
		
		
		
	}
	
	public void nextWave(){
		currentWaveIndex++;
		if(currentWaveIndex >= currentLevel.getWavesAmount()){
			nextLevel();
			currentWaveIndex = 0;
		}
		wave = currentLevel.getNextWave(true);
		enemyCounter = 0;
		
	}
		
	private void nextLevel(){
		currentLevel = levels.pop();
	}
	@Override
	public boolean keyDown (int keycode) {
		if(keycode == Keys.SPACE){
			nextWave();
			waveON = true;
			return true;
		}
		return false;
	}
	
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
}
