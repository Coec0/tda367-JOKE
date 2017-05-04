package utilities;

import com.badlogic.gdx.utils.Array;

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
	
	public boolean inNodeArrayRadius(Array<Node> nodes, float nodesRadius , Node center ,float centerRadius){
		for(Node node : nodes){
			if(isNodeWithinRadius(center,node,centerRadius,nodesRadius)){
				return true;
			}
		}
		return false;
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
