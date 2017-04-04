package utilities;

import com.badlogic.gdx.utils.Array;

public final class PathFinder {
	private static final PathFinder instance = new PathFinder();

	private Array<Node> shortestPath;
	private Array<Node> allNodes;
	private float speed;
	private Array<Node> directionList = new Array<Node>(); 

	private PathFinder() {
		allNodes = new Array<Node>();
	}

	public static PathFinder getInstance() {
		return instance;
	}

//	public PathFinder(Array<Node> allNodes){
//			this.allNodes = allNodes;
//	}
	
	public void setSpeed(float speed){
		this.speed = speed;
	}
	
	public void calculateShortest(Array<Node> nodes){
		shortestPath = getFullPath(10, nodes); //just for testing. will be replaced with dijekstra
	}

	public Array<Node> getShortestPath(){
		
//		if (shortestPath == null){
//			calculateShortest();
//			System.out.println(shortestPath.size);
//		}
//		System.out.println(shortestPath.size);
		return shortestPath;
	}
	
	public void setAllNodes(Array<Node> allNodes){
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
		Node direction = normalizedNode(start,goal);

		float currentDistance = 0;

		while(currentDistance <= getDistance(start, goal)) {
			Node nextStep = new Node(current.getX() + (direction.getX() * speed), current.getY() + (direction.getY() * speed));

			pixelPath.add(nextStep);

			directionList.add(direction);

			current = nextStep;
			currentDistance = (float) getDistance(start, current);
		}
		pixelPath.pop();
		pixelPath.add(goal);

		return pixelPath;
	}

	/**
	 * Returns the distance between two given nodes
	 * @param start
	 * @param goal
	 * @return distance
	 */
	private double getDistance(Node start, Node goal) {
		float deltaX = getDeltaX(start, goal);
		float deltaY = getDeltaY(start, goal);

		return Math.sqrt(deltaX*deltaX + deltaY*deltaY);
	}

	/**
	 * Returns the difference on the x-axis between two given nodes
	 * @param start
	 * @param goal
	 * @return deltaX
	 */
	private float getDeltaX(Node start, Node goal) {
		return goal.getX() - start.getX();
	}

	/**
	 * Returns the difference on the y-axis between two given nodes
	 * @param start
	 * @param goal
	 * @return deltaY
	 */
	private float getDeltaY(Node start, Node goal) {
		return goal.getY() - start.getY();
	}

	/**
	 * Returns Node with normalized direction
	 * @param start
	 * @param goal
	 * @return Node with normalized direction
	 */
	private Node normalizedNode(Node start, Node goal) {
		float startDistance = (float) getDistance(start, goal);
		float deltaX = getDeltaX(start, goal);
		float deltaY = getDeltaY(start, goal);

		return new Node(deltaX/startDistance, deltaY/startDistance);
	}
	
	public Array<Node> getDirectionList(){
		return this.directionList;
	}
	

	
	
	
	
	
	
	
	
		
}
