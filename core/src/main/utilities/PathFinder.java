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
