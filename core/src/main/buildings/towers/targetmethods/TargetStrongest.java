package buildings.towers.targetmethods;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import utilities.Node;

/**
 * Created by Emil on 2017-03-31.
 */
public class TargetStrongest implements ITargetState  {

	@Override
	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {
		float strongest = enemies.first().getHealth();
		Enemy strongestE = enemies.first();
        for (Enemy enemy : enemies){
		    if(enemy.getHealth() > strongest){
		        strongest = enemy.getHealth();
		        strongestE = enemy;
            }
        }
		return strongestE;
	}

}
