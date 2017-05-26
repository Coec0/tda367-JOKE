package com.example.illegalaliens.utilities;

import java.awt.Point;

public class Node{
	private float x;
	private float y;
	
	public Node(float x, float y){
		this.y = y;
		this.x = x;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(x);
		result = prime * result + Float.floatToIntBits(y);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (Float.floatToIntBits(x) != Float.floatToIntBits(other.x))
			return false;
		if (Float.floatToIntBits(y) != Float.floatToIntBits(other.y))
			return false;
		return true;
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

	public float getDeltaX(Node other) {
		return other.x - this.x;
	}

	public float getDeltaY(Node other) {
		return other.y - this.y;
	}

}
