package buildings.towers.targetmethods;

import com.badlogic.gdx.utils.Array;

import buildings.towers.Tower;
import enemies.Alien;

/**
 * Created by Emil on 2017-03-31.
 */
public class TargetFirst implements ITargetState {

	@Override
	public Alien getEnemy(Tower tower) {

		Array<Alien> Aliens = tower.getRadar().scan(tower.getPos(), tower.getRadius());
		Alien closest = Aliens.first();
		float closestDist = tower.getRadar().getDistance(tower.getPos(), closest.getPos());
		float dist;
		for (Alien alien : Aliens) {
			dist = tower.getRadar().getDistance(tower.getPos(), alien.getPos());
			if (dist < closestDist) {
				closestDist = dist;
				closest = alien;
			}
		}
		return closest;
	}

}
