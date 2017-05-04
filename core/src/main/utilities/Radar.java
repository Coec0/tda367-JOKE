package utilities;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import models.AlienModel;

public class Radar {
	


	public Radar(){}

	/**
	 * Scans for all Enemies known by AlienModel.
	 * @param center
	 * @param radius
	 * @return array of known enemies
	 */
	public Array<Enemy> scan(Node center, float radius,Array<Enemy> enemies){
		Array<Enemy> knownEnemies = new Array<Enemy>();
		
		for(Enemy enemy : enemies) {
			if (isEnemyWithinRadius(center, enemy, radius)) {
				knownEnemies.add(enemy);
			}
		}

		return knownEnemies;
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
