package com.example.illegalaliens.utilities;

import com.badlogic.gdx.utils.Array;

public class Radar {

	/**
	 * Scans for all Enemies known by AlienModel.
	 * @param center
	 * @param radius
	 * @return array of known enemies
	 */
	public Array<Node> scan(Node center, float centerRadius,Array<Node> nodes , float[] nodesRadius){
		Array<Node> knownNodes = new Array<Node>();
		
		for(int i = 0;i < nodes.size; i++){
			if (isNodeWithinRadius(center, centerRadius, nodes.get(i), nodesRadius[i])) {
				knownNodes.add(nodes.get(i));
			}
		}
		return knownNodes;
    }
	
	public Array<Node> scan(Node center, float centerRadius, Array<Node> nodes, float nodesRadius){
		Array<Node> foundNodes = new Array<Node>();
		for(Node node : nodes){
			if(isNodeWithinRadius(center,centerRadius ,node,nodesRadius)){
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
    public boolean isNodeWithinRadius(Node center, float centerRadius ,Node other , float otherRadius ) {
		return center.getDistanceTo(other) <= centerRadius + otherRadius;
	}

}
