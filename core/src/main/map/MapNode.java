package map;

import com.badlogic.gdx.utils.Array;

import utilities.Node;

public class MapNode {

	private String ID;
	private Node pos;
	private Array<String> neighbors;
	private String prevID;
	private float pathLength;
	private boolean visited;
	private String lastRemovedNeighbor;
	
	public MapNode(String ID, Node pos){
		this.ID = ID;
		this.pos = pos;
		neighbors = new Array<String>();
		pathLength = Float.MAX_VALUE; //might have to find better solution to inf
		visited = false;
	}

	public float getPathLength() {
		return pathLength;
	}
	
	public void reset(){
		pathLength = Float.MAX_VALUE;
		visited = false;
	}
	
	public void visit(){
		visited = true;
	}
	
	public boolean hasBeenVisited() {
		return visited;
	}
	
	public void setPathLength(float pathLength) {
		this.pathLength = pathLength;
	}
	
	public String getPrevID() {
		return prevID;
	}
	
	public void setPrevID(String prevID) {
		this.prevID = prevID;
	}
	
	public String getID() {
		return ID;
	}

	public Node getPos() {
		return pos;
	}
	
	public void removeNeighbor(String neighbor){
		for(String string : neighbors){
			if(string.equals(neighbor)){
				neighbors.removeValue(neighbor, false);
				lastRemovedNeighbor = neighbor;
			}
		}
	}
	
	public String getLastRemovedNeighbor(){
		return lastRemovedNeighbor;
	}
	
	public void setNeighbors(Array<String> neighbors){
		this.neighbors = neighbors;
	}
	
	public void addNeighbor(String neighbor){
		neighbors.add(neighbor);
	}
	
	public Array<String> getNeighbors(){
		return neighbors;
	}
	
	public float getDistanceTo(MapNode other) {
		return (float) pos.getDistanceTo(other.getPos());
	}
	
	
}
