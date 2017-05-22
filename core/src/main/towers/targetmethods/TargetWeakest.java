package towers.targetmethods;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import utilities.Node;

/**
 * Created by Emil on 2017-03-31.
 */

/**
 * Targets the weakest enemy available
 */
public class TargetWeakest implements ITargetState  {

	@Override
	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {
		float weakest = enemies.first().getHealth();
		Enemy weakestE = enemies.first();
		for (Enemy enemy : enemies){
			if(enemy.getHealth() < weakest){
				weakest = enemy.getHealth();
				weakestE = enemy;
			}
		}
		return weakestE;
	}

	@Override
	public String toString() {
		return "Weakest";
	}
}
