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

}
