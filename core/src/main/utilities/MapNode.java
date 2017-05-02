package utilities;

import com.badlogic.gdx.utils.Array;

public class MapNode {
	private String ID;
	private Node pos;
	private Array<String> neighbors;
	private String prevID;
	private float pathLenght;
	private boolean visited = false;
	
	public MapNode(String ID, Node pos){
		this.ID = ID;
		this.pos = pos;
		neighbors = new Array<String>();
		pathLenght = Float.MAX_VALUE; //might have to find better solution to inf
	}
	public float getPathLenght() {
		return pathLenght;
	}
	
	public void reset(){
		pathLenght = Float.MAX_VALUE;
		visited = false;
	}
	
	public void visit(){
		visited = true;
	}
	
	public boolean Visited(){
		return visited;
	}
	
	public void setPathLenght(float pathLenght) {
		this.pathLenght = pathLenght;
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
