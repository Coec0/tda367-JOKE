package utilities;

import java.awt.Point;

public class Node{
	private float x;
	private float y;
	
	public Node(float x, float y){
		this.y = y;
		this.x = x;
	}
	
	public Node(int x , int y){
		this.x = x;
		this.y = y;
	}
	
	public Node(Point point){
		this.x = (float) point.getX();
		this.y = (float) point.getY();
	}
	public void setCord(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public float getX(){
		return x;
	}
	
	public float getY(){
		return y;
	}

	public double getLength() {
		return Math.sqrt(x * x + y * y);
	}

	/**
	 * Returns the distance to another node
	 * @param other
	 * @return distance
	 */
	public double getDistanceTo(Node other) {
		float dx = getDeltaX(other);
		float dy = getDeltaY(other);

		return Math.sqrt(dx*dx + dy*dy);
	}

	/**
	 * Returns a new Node with normalized direction
	 * @param other
	 * @return Node with normalized direction
	 */
	public Node getAsNormalizedNode(Node other) {
		float startDistance = (float) getDistanceTo(other);
		float dx = getDeltaX(other);
		float dy = getDeltaY(other);

		return new Node(dx/startDistance, dy/startDistance);
	}

	private float getDeltaX(Node other) {
		return other.x - this.x;
	}

	private float getDeltaY(Node other) {
		return other.y - this.y;
	}

}
