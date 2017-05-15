package towers.targetmethods;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import utilities.Node;

public class TargetLast implements ITargetState {

	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {
		int last=enemies.first().getPath().size - enemies.first().getNodeArrayPos();
		Enemy lastE = enemies.first();
		for(Enemy enemy: enemies){
			if(enemy.getPath().size - enemy.getNodeArrayPos()>last){
				last = enemy.getPath().size - enemy.getNodeArrayPos();
				lastE = enemy;
			}
		}
		return lastE;
	}
}
