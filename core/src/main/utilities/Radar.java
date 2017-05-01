package utilities;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import models.AlienModel;

public class Radar {
	
	private AlienModel aModel;

	public Radar(AlienModel aModel){
		this.aModel = aModel;
	}

	/**
	 * Scans for all Enemies known by AlienModel.
	 * @param center
	 * @param radius
	 * @return array of known enemies
	 */
	public Array<Enemy> scan(Node center, float radius){
		Array<Enemy> knownAliens = new Array<Enemy>();
		
		for(Enemy alien : aModel.getAllAliens()) {
			if (isEnemyWithinRadius(center, alien, radius)) {
				knownAliens.add(alien);
			}
		}

		return knownAliens;
    }

	/**
	 * Checks if Alien is within radius from Node. All Aliens it's own radius because of Texture.
	 * @param center
	 * @param alien
	 * @param radius
	 * @return true if within radius
	 */
    public boolean isEnemyWithinRadius(Node center, Enemy alien, float radius) {
		return center.getDistanceTo(alien.getPos()) <= radius + alien.getRadius();
	}

}
