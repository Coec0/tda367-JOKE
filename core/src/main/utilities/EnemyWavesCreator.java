package utilities;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.utils.Array;

import controllers.AlienController;
import enemies.Alien;
import enemies.Enemy;

public class EnemyWavesCreator extends InputAdapter implements UpdateObserver  {
	

	private AlienController cont;
	private int counter = 1;
	private int frames;
	
	public EnemyWavesCreator(AlienController cont){
		this.cont = cont;
	}
	public void createWave(Alien alien, int amount){
	}
	
	public void nextWave(){
			counter = 1;
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
	
	@Override
	public boolean keyDown (int keycode) {
		if(keycode == Keys.SPACE){	
			nextWave();
			return true;
		}
		return false;
	}
}
