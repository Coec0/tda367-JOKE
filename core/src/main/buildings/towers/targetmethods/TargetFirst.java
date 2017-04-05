package buildings.towers.targetmethods;

import com.badlogic.gdx.utils.Array;

import enemies.Enemy;
import utilities.Node;

public class TargetFirst implements ITargetState{

	@Override
	public Enemy getEnemy(Node pos, Array<Enemy> enemies) {
		int first=enemies.first().getNodeArrayPos();
		Enemy firstE = enemies.first();
		for(Enemy enemy: enemies){
			if(enemy.getNodeArrayPos()>first){
				first = enemy.getNodeArrayPos();
				firstE = enemy;
			}
		}
		return firstE;
	}

}
