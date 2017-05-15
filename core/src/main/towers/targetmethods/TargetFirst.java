package towers.targetmethods;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import utilities.Node;

public class TargetFirst implements ITargetState{

	@Override
	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {
		int first=enemies.first().getPath().size - enemies.first().getNodeArrayPos();
		Enemy firstE = enemies.first();
		for(Enemy enemy: enemies){
			if(enemy.getPath().size - enemy.getNodeArrayPos()<first){
				first = enemy.getPath().size - enemy.getNodeArrayPos();
				firstE = enemy;
			}
		}
		return firstE;
	}

}
