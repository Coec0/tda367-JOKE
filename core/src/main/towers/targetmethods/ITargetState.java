package towers.targetmethods;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import utilities.Node;

/**
 * Created by Emil on 2017-03-31.
 */

/**
 * Interface implemented in all target states, using State Pattern
 */
public interface ITargetState {
	Enemy getEnemy(Node pos, Array<Enemy> enemies); //Maybe edit to Building in future
}
