package buildings.towers.targetmethods;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import utilities.Node;

public class TargetLast implements ITargetState {

	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {
		int last=enemies.first().getNodeArrayPos();
		Enemy lastE = enemies.first();
		for(Enemy enemy: enemies){
			if(enemy.getNodeArrayPos()<last){
				last = enemy.getNodeArrayPos();
				lastE = enemy;
			}
		}
		return lastE;
	}
}
