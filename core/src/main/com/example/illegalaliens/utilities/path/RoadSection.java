package com.example.illegalaliens.utilities.path;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.utilities.Node;

public class RoadSection {
	private Array<Node> pixelWalk;
	private Node start;
	private Node end;
	private float speed;
	
	
	public RoadSection(Node start, Node end, float speed){
		this.start = start;
		this.end = end;
		this.speed = speed;
	}
	
	/**
	 * Calculates and saves all points inbetween start and end with the distance speed
	 * @param start Starting node
	 * @param end End node
	 * @param speed	Distance inbetween "jumps" in the pixelwalk. 
	 * @return	The pixelwalk in form of node array
	 */
	public Array<Node> calcPixelWalk(Node start, Node end, float speed){
		Array<Node> pixelPath = new Array<Node>();

		Node current = start;
		Node direction = start.getAsNormalizedNode(end);

		float currentDistance = 0;

		while(currentDistance <= start.getDistanceTo(end)) {
			Node nextStep = new Node(
					current.getX() + (direction.getX() * speed),
					current.getY() + (direction.getY() * speed)
			);

			pixelPath.add(nextStep);
			current = nextStep;
			currentDistance = (float) start.getDistanceTo(current);
		}
		pixelPath.pop();
		pixelPath.add(end);

		return pixelPath;
	}
	
	public boolean isStartOrEnd(Node node){
		
		return node.equals(start) || node.equals(end);
	}

	public float getSpeed() {
		return speed;
	}
	public void setSpeed(float speed) {
		this.speed = speed;
		pixelWalk = calcPixelWalk(start,end,speed);
	}
	public Node getEnd() {
		return end;
	}
	
	public Node getStart() {
		return start;
	}
	
	public Array<Node> getPixelWalk() {
		if(pixelWalk == null){
			pixelWalk = calcPixelWalk(start,end,speed);
		}
		return pixelWalk;
	}
	
	public boolean isEndsInRoadSection(Node nodeA , Node nodeB){
		return isStartOrEnd(nodeA) && isStartOrEnd(nodeB);
	}
	
}
