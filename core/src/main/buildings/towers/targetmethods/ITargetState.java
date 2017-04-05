package buildings.towers.targetmethods;

import buildings.towers.Tower;
import enemies.Alien;

/**
 * Created by Emil on 2017-03-31.
 */
public interface ITargetState {
	Alien getEnemy(Tower tower); //Maybe edit to Building in future
}
