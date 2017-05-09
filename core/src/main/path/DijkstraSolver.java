package path;

import com.badlogic.gdx.utils.Array;

import map.MapNode;
import utilities.Node;

public class DijkstraSolver {
	private Array<MapNode> allNodes;
	private MapNode startNode;
	private MapNode endNode;
	private MapNode currentNode;
	
	public DijkstraSolver(){
	}
	
	public Array<Node> solve(MapNode startNode, MapNode endNode,Array<MapNode> allNodes){
		
		this.allNodes = allNodes;
		
		this.startNode = startNode;
		this.endNode = endNode;
		resetMapNodes();
		
		if(startNode.getNeighbors().size == 0){		//return empty if start has no neighbors. 
													//no need to waste computing power if this is the case
			System.out.println("Dijkstra: start no neighbors");
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
			System.out.println("Dijkstra: end pathlenght inf");
			return new Array<Node>();
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
