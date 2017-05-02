package utilities;

import com.badlogic.gdx.utils.Array;

public final class PathFinder {
	

	private Array<Array<Node>> shortestPaths;
	private Array<MapNode> allNodes;
	private Array<Node> directionList = new Array<Node>(); 
	private DijkstraSolver DSolver;
	private MapNode endNode;
	

	public PathFinder(Array<MapNode> allNodes, MapNode endNode) {
		this.endNode = endNode;
		this.allNodes = allNodes;
		shortestPaths = new Array<Array<Node>>();
		DSolver = new DijkstraSolver(allNodes);
		
	}



//	public PathFinder(Array<Node> allNodes){
//			this.allNodes = allNodes;
//	}
	
	
	
	public void calculateShortest(MapNode startNode){
		Array<Node> tmp = DSolver.solve(startNode, endNode);
		shortestPaths.add(getFullPath(10,tmp)); //10 = speed
	}

	public Array<Node> getShortestPath(MapNode startNode){
		
		if(shortestPaths.size == 0){ //check if empty to prevent nUllpointer
			calculateShortest(startNode);
			return shortestPaths.get(0); //might as well return right away to save some time
		}

		for(Array<Node> nodeArray : shortestPaths){
			System.out.println("PathFinder: " + nodeArray.get(0).getX() + " : " + nodeArray.get(0).getY());
			if(nodeArray.get(0).equals(startNode.getPos())){ //checks if startingNode in each calculated path is the same as startNode
				System.out.println("PathFinder: hejehj");
				return nodeArray;
			}
		}
		
		calculateShortest(startNode); //now it only calculates if the path did not exist before
		System.out.println("PathFinder: " + shortestPaths.peek().get(0).getX() + " " + shortestPaths.peek().get(0).getY());
		return shortestPaths.peek(); //returns the the path we just added
	}
	
	public void setAllNodes(Array<MapNode> allNodes){
		this.allNodes = allNodes;
	}
	
	//calculates the specific coordinates between nodes with a certain jump

	/**
	 * Returns the full path of pixels between multiple Nodes
	 * @param speed
	 * @param pathNodes
	 * @return full path
	 */
	private Array<Node> getFullPath(float speed, Array<Node> pathNodes){
		
		Array<Node> fullPath = new Array<Node>();
		directionList.clear(); //because directionList has to match path
		
		for (int i = 0; i < pathNodes.size - 1; i++) {
			Node start = pathNodes.get(i);
			Node goal = pathNodes.get(i+1);

			Array<Node> pixelPath = getPixelPath(start, goal, speed);
				
			for (Node pixel : pixelPath) {
				fullPath.add(pixel);
			}
		}
	
		return fullPath;
	}

	/**
	 * Returns the pixels between two given Nodes
	 * @param start
	 * @param goal
	 * @return
	 */
	private Array<Node> getPixelPath(Node start, Node goal, float speed) {
		Array<Node> pixelPath = new Array<Node>();

		Node current = start;
		Node direction = start.getAsNormalizedNode(goal);

		float currentDistance = 0;

		while(currentDistance <= start.getDistanceTo(goal)) {
			Node nextStep = new Node(
					current.getX() + (direction.getX() * speed),
					current.getY() + (direction.getY() * speed)
			);

			pixelPath.add(nextStep);

			directionList.add(direction);

			current = nextStep;
			currentDistance = (float) start.getDistanceTo(current);
		}
		pixelPath.pop();
		pixelPath.add(goal);

		return pixelPath;
	}
	
	public Array<Node> getDirectionList(){
		return this.directionList;
	}
}
