package com.example.illegalaliens.utilities.path;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.path.map.MapNode;

public class DijkstraSolver {

	private Array<MapNode> allNodes;
	private MapNode currentNode;
	
	/**
	 * 
	 * @param startNode The node that dijkstra will start from.
	 * @param endNode	The node that dijkstra will stop at.
	 * @param allNodes	The network in which dijkstra will look for path
	 * @return	I a path is found the path will be returned else empty array.
	 */
	public Array<Node> solve(MapNode startNode, MapNode endNode, Array<MapNode> allNodes){
		this.allNodes = allNodes;

		resetMapNodes();
		
		if(startNode.getNeighbors().size == 0){		//return empty if start has no neighbors. 
													//no need to waste computing power if this is the case
			return new Array<Node>();
		}
		
		startNode.setPathLength(0);
		setCurrentNode(startNode);
		weighNeighbors();
		
		while(!isDone()){
			MapNode nextCurrent = findNextCurrent();
			setCurrentNode(nextCurrent);
			weighNeighbors();
		}
		
		if(endNode.getPathLength() == Float.MAX_VALUE){ //return empty if we never found a way to the endNode
			return new Array<Node>();
		}
		
		return findPath(startNode, endNode);
	}
	
	private void weighNeighbors(){
		MapNode neighbor;
		for(String ID : currentNode.getNeighbors()){
			neighbor = findNode(ID);
			currentNode.weighNeighbor(neighbor);
		}
	}

	private MapNode findNode(String ID){
		for(MapNode MN : allNodes){
			if(MN.getID().equals(ID)){
				return MN;
			}
		}
		return null;
	}

	private void setCurrentNode(MapNode node){
		currentNode = node;
		node.visit();
	}
	
	private void resetMapNodes(){
		for(MapNode MN : allNodes){
			MN.reset();
		}
	}
	
	private MapNode findNextCurrent(){
		float currentMinValue = Float.MAX_VALUE;
		MapNode prospect = currentNode; //just to have a starting value
		for(MapNode node : allNodes){
			if( !node.hasBeenVisited() && node.getPathLength() <= currentMinValue){
				prospect = node;
				currentMinValue = node.getPathLength();
			}
		}

		return prospect;
	}
	
	private boolean isDone(){
		Array<MapNode> inf = new Array<MapNode>();
		Array<MapNode> nonVisited = new Array<MapNode>();
		
		for(MapNode node : allNodes){
			
			if(!node.hasBeenVisited()){
				nonVisited.add(node);						
			}
			if(node.getPathLength() == Float.MAX_VALUE){
				inf.add(node);
			}
		}
		
		//these two are our end conditions.
		if(nonVisited.size == inf.size){	//return true if all nonvisited nodes have pathlenght inf
			return true;
		}
		if(nonVisited.size == 0){ //return true if we have visited all nodes
			return true;
		}
		return false; 
	}
	
	private Array<Node> findPath(MapNode startNode, MapNode endNode){
		MapNode tmpNode = endNode;
		Array<Node> path = new Array<Node>();
		String prevID;
		
		while( !tmpNode.equals(startNode)){
			
			path.add(tmpNode.getPos());
			prevID = tmpNode.getPrevID();
			tmpNode = findNode(prevID);
			
		}
		path.add(startNode.getPos());
		path.reverse();
		return path;	
	}
}
