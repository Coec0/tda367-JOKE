package utilities;

import com.badlogic.gdx.utils.Array;

public class MapNode {
	private String ID;
	private Node pos;
	private Array<String> neighbors;
	private String prevID;
	private float weight;
	
	public MapNode(String ID, Node pos){
		this.ID = ID;
		this.pos = pos;
		neighbors = new Array<String>();
		weight = Float.MAX_VALUE; //might have to find better solution to inf
	}
	public float getWeight() {
		return weight;
	}
	
	public void setWeight(float weight) {
		this.weight = weight;
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
	
	
}
