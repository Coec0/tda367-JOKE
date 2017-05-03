package utilities;

import com.badlogic.gdx.utils.Array;

public class DijkstraSolver {
	private Array<MapNode> allNodes;
	private MapNode startNode;
	private MapNode endNode;
	private MapNode currentNode;
	
	public DijkstraSolver(Array<MapNode> allNodes){
		this.allNodes = allNodes;
	}
	
	public Array<Node> solve(MapNode startNode, MapNode endNode){
		resetMapNodes();
		this.startNode = startNode;
		this.endNode = endNode;
		startNode.setPathLength(0);
		setCurrentNode(startNode);
		weighNeighbors();
		
		while(isDone()){
			setNextCurrent();
			weighNeighbors();
		}
		return findPath();
	}
	
	private void weighNeighbors(){
		MapNode neighbor;
		for(String ID : currentNode.getNeighbors()){
			neighbor = findNode(ID);
			weighNeighbor(neighbor);	
		}
	}
	
	
	private void weighNeighbor(MapNode node){
		if(currentNode.getPathLength() + currentNode.getDistanceTo(node) < node.getPathLength()){
			node.setPathLength(currentNode.getPathLength() + currentNode.getDistanceTo(node));
			node.setPrevID(currentNode.getID());
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
	
	private void setNextCurrent(){
		float currentMinValue = Float.MAX_VALUE;
		MapNode prospect = currentNode; //just to have a starting value
		for(MapNode node : allNodes){
			if( !node.hasBeenVisited() && node.getPathLength() <= currentMinValue){
				prospect = node;
				currentMinValue = node.getPathLength();
			}
		}

		setCurrentNode(prospect);
	}
	
	private boolean isDone(){
		int counter = 0;
		for(MapNode node : allNodes){
			if(!node.hasBeenVisited() && node.getPathLength() == Float.MAX_VALUE){	//return true if all nonvisited nodes have pathlenght inf
				return true;
			}else if(node.hasBeenVisited()){
				counter++;
			}
		}
		
		if(counter == allNodes.size){ //return true if we have visited all nodes
			return true;
		}
		
		return false;
	}
	
	private Array<Node> findPath(){
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
