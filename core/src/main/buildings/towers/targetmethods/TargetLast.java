package buildings.towers.targetmethods;

import com.badlogic.gdx.utils.Array;

import buildings.towers.Tower;
import enemies.Alien;

/**
 * Created by Emil on 2017-03-31.
 */
public class TargetLast implements ITargetState {

	@Override
	public Alien getEnemy(Tower tower) {

		Array<Alien> Aliens = tower.getRadar().scan(tower.getPos(), tower.getRadius());
		Alien furthest = Aliens.first();
		float furthestDist = tower.getRadar().getDistance(tower.getPos(), furthest.getPos());
		float dist;
		for (Alien alien : Aliens) {
			dist = tower.getRadar().getDistance(tower.getPos(), alien.getPos());
			if (dist > furthestDist) {
				furthestDist = dist;
				furthest = alien;
			}
		}
		return furthest;
	}
}
