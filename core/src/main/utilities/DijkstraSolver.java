package utilities;

import com.badlogic.gdx.utils.Array;

public class DijkstraSolver {
	private Array<MapNode> allNodes;
	private MapNode startNode;
	private MapNode endNode;
	private MapNode currentNode;
	private boolean searching = true;
	
	public DijkstraSolver(Array<MapNode> allNodes){
		this.allNodes = allNodes;
	}
	
	public Array<Node> solve(MapNode startNode, MapNode endNode){
		resetMapNodes();
		
		this.startNode = startNode;
		this.endNode = endNode;
		startNode.setPathLenght(0);
		setCurrentNode(startNode);
		weighNeighbors();
		
		while(true){
			if(pickNewCurrentNode()){
				break;
			}
			weighNeighbors();
			
		}
		return findPath();
	}
	
	private Array<Node> findPath(){
		MapNode tmpNode = endNode;
		Array<Node> path = new Array<Node>();
		String prevID;
		
		while( !tmpNode.equals(startNode)){
			
			path.add(tmpNode.getPos());
			prevID = tmpNode.getPrevID();
			System.out.println(prevID);
			tmpNode = findNode(prevID);
			
		}
		path.add(startNode.getPos());
		path.reverse();
		return path;	
	}
	
	
	private void weighNeighbors(){
		MapNode neighbor;
		for(String ID : currentNode.getNeighbors()){
			neighbor = findNode(ID);
			weighNeighbor(neighbor);	
		}
	}
	
	private boolean pickNewCurrentNode(){
		MapNode neighbor;
		MapNode prospect = currentNode; //just to have a starting value
		float minPathLength = Float.MAX_VALUE;
		for(String ID : currentNode.getNeighbors()){
			neighbor = findNode(ID);
			if( !neighbor.Visited() ){   //&& neighbor.getPathLenght() <= minPathLength){ 
				System.out.println("DijkstraSolver: hejdåre");
				minPathLength = neighbor.getPathLenght();
				prospect = neighbor;
				
				
			}
			
		}
		
		if(prospect.equals(currentNode)){
			return true;
		}
		
		setCurrentNode(prospect);
		return false;
	}
	
	private void weighNeighbor(MapNode node){
		if(currentNode.getPathLenght() + currentNode.getDistanceTo(node) < node.getPathLenght()){
			node.setPathLenght(currentNode.getPathLenght() + currentNode.getDistanceTo(node));
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
}
