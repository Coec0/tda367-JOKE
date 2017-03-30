package utilities;

import com.badlogic.gdx.utils.Array;

public class PathFinder{
	private Array<Node> shortestPath;
	private Array<Node> allNodes;
	private float pixelJump = 5; //replacement for speed
	
	public PathFinder(Array<Node> allNodes){
			this.allNodes = allNodes;
	}
	
	private void calculateShortest(){
		shortestPath = calcPixelPath(allNodes.get(0), allNodes); //just for testing. will be replaced with dijekstra
	}
	
	public Array<Node> getShortestPath(){
		if (shortestPath != null){
			calculateShortest();
		}
		return shortestPath;
	}
	
	public void setAllNodes(Array<Node> allNodes){
		this.allNodes = allNodes;
	}
	
	//Calc pixels for sprites to jump to. Assumes speed 1
	private Array<Node> calcPixelPath(Node firstNode, Array<Node> pathNodes){ //perhaps split this into an own class
		
		Array<Node> path = new Array<Node>();
		
		for(Node goal : pathNodes){
			Node current = firstNode;
			Node start = firstNode;
			Node delta = new Node(goal.getX() - start.getX(), goal.getY() - start.getY()); 
			float startDistance = (float) Math.sqrt(delta.getX()*delta.getX() + delta.getY()*delta.getY()); //bad converting???
			Node direction = new Node(delta.getX()/startDistance, delta.getY()/startDistance);
			
			Node deltaCurrent = new Node(current.getX() - start.getX(), current.getY() - start.getY());
			float currentDistance = (float) Math.sqrt(deltaCurrent.getX()*deltaCurrent.getX() + deltaCurrent.getY()*deltaCurrent.getY());
			
			while(currentDistance <= startDistance){
					Node nextStep = new Node(current.getX() * direction.getX() * this.pixelJump, current.getY() * direction.getY() * this.pixelJump);
					path.add(nextStep);
					current = nextStep;
					deltaCurrent = new Node(current.getX() - start.getX(), current.getY() - start.getY());
					currentDistance = (float) Math.sqrt(deltaCurrent.getX()*deltaCurrent.getX() + deltaCurrent.getY()*deltaCurrent.getY()); 
			} //now might jump over goalNode
			
			
		}
		
		return path;
		
		
	}
	
	
	
	
	
	
	
	
		
}
