package utilities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;

import controllers.AlienController;
import enemies.Alien;
import enemies.Enemy;

public class EnemyWavesCreator extends InputAdapter implements UpdateObserver  {
	

	private AlienController cont;
	private int counter = 0;
	private int frames;
	
	private Array<Level> levels = new Array<Level>();
	
	private int wavesLeft;
	private int currentLevel;
	
	private Array<Enemy> wave;
	
	
	
	public EnemyWavesCreator(AlienController cont){
		Array<Enemy> tmpEnemies = new Array<Enemy>();
		tmpEnemies.add(new Alien());
		levels.add(new Level(1,5,tmpEnemies)); //levels has to be hardcoded. Maybe in textfile in future?
		levels.add(new Level(2,7,tmpEnemies));
		this.cont = cont;
		currentLevel = 0;
		wavesLeft = levels.get(currentLevel).getWavesAmount();
	}
	
	
	public void createWave(){
		
	}
	
	public void nextWave(){
			counter = 10;
	}
		
	
	@Override
	public boolean keyDown (int keycode) {
		if(keycode == Keys.SPACE){	
			nextWave();
			return true;
		}
		return false;
	}
	
	@Override
	public void update(float deltaTime) {
		frames++;
		if(frames > 10){
			if(counter > 0){
				cont.spawnAlien();
				counter--;
			}
			frames = 0;
			
		}
	}
}
