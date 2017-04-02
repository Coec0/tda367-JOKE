package utilities;

import com.badlogic.gdx.utils.Array;

import controllers.AlienController;
import enemies.Alien;
import enemies.Enemy;

public class EnemyWavesCreator implements UpdateObserver {
	
	private int wave;
	private Array<Enemy> enemies;
	private AlienController cont;
	private int counter = 10;
	private int frames;
	
	public EnemyWavesCreator(AlienController cont){
		this.cont = cont;
	}
	public void createWave(Alien alien, int amount){
	}
	
	public void nextWave(){
			counter = 10;
	}
		
	@Override
	public void update(float deltaTime) {
		frames++;
		if(frames > 15){
			if(counter > 0){
				cont.spawnAlien();
				counter--;
			}
			frames = 0;
			
		}
	}
}
