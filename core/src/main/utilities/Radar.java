package utilities;

import com.badlogic.gdx.utils.Array;

import boardobjects.towers.Tower;
import enemies.Enemy;

public class Radar {

	/**
	 * Scans for all Enemies known by AlienModel.
	 * @param center
	 * @param radius
	 * @return array of known enemies
	 */
	public Array<Enemy> scan(Node center, float radius,Array<Enemy> enemies){
		Array<Enemy> knownEnemies = new Array<Enemy>();
		
		for(Enemy enemy : enemies) {
			if (isNodeWithinRadius(center, enemy.getPos(), radius,enemy.getRadius())) {
				knownEnemies.add(enemy);
			}
		}
		return knownEnemies;
    }

    public Array<Tower> towerScan(Node center, float radius, Array<Tower> towers){
		Array<Tower> knownTowers = new Array<Tower>();

		for(Tower tower: towers){
			if(isNodeWithinRadius(center, tower.getPos(), radius, tower.getRadius())){
				knownTowers.add(tower);
			}
		}

		return knownTowers;
	}
	
	public Array<Node> scanNodeArray(Array<Node> nodes, float nodesRadius , Node center ,float centerRadius){
		Array<Node> foundNodes = new Array<Node>();
		for(Node node : nodes){
			if(isNodeWithinRadius(center,node,centerRadius,nodesRadius)){
				foundNodes.add(node);
			}
		}
		return foundNodes;
	}
	

	/**
	 * Checks if Alien is within radius from Node. All Aliens it's own radius because of Texture.
	 * @param center
	 * @param other
	 * @param centerRadius
	 * @param otherRadius
	 * @return true if within radius
	 */
    public boolean isNodeWithinRadius(Node center, Node other, float centerRadius, float otherRadius ) {
		return center.getDistanceTo(other) <= centerRadius + otherRadius;
	}

}
