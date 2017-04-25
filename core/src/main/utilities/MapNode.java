package utilities;

public class MapNode {
	private String ID;
	private Node pos;
	private String prevID;
	private float weight;
	
	public MapNode(String ID, Node pos){
		this.ID = ID;
		this.pos = pos;
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
	
	
}
