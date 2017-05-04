package utilities;

import com.badlogic.gdx.utils.Array;

public final class PathFinder {
	

	private Array<Array<Node>> shortestPaths;
	private Array<Node> startingNodes;
	private Array<MapNode> allNodes;
	private Array<Node> roadNetwork;

	private DijkstraSolver DSolver;
	private MapNode endNode;
	private Radar radar;
	

	public PathFinder(Array<MapNode> allNodes, MapNode endNode) {
		this.endNode = endNode;
		this.allNodes = allNodes;
		shortestPaths = new Array<Array<Node>>();
		startingNodes = new Array<Node>();
		DSolver = new DijkstraSolver(allNodes);
		radar = new Radar();
		
	}

	public boolean isOnRoad(Node center, float centerRadius, float roadRadius){
		if(roadNetwork == null){
			roadNetwork = new Array<Node>();
			roadNetwork = getRoadNetwork();
		}
		if(radar.inNodeArrayRadius(roadNetwork, roadRadius, center, centerRadius)){
			return true;
		}
		return false;
		
	}
	
	private MapNode findNode(String ID){
		for(MapNode MN : allNodes){
			if(MN.getID().equals(ID)){
				return MN;
			}
		}
		return null;
	}
	
	public Array<Node> getRoadNetwork(){
		Array<Node> roadNetwork = new Array<Node>();
		for(int i = 0; i < allNodes.size;i++){
			MapNode mn = allNodes.get(i);
			for(String neighbor : mn.getNeighbors()){
				MapNode tmp  = findNode(neighbor);
				Array<Node> pixelPath = getPixelPath(mn.getPos(), tmp.getPos(),1); //Have to adjust speed to optimize
				for(Node node : pixelPath){
					roadNetwork.add(node);
				}
			}
		}
		return roadNetwork;
	}

//	public PathFinder(Array<Node> allNodes){
//			this.allNodes = allNodes;
//	}
	
	
	
	public void calculateShortest(MapNode startNode){
		Array<Node> tmp = DSolver.solve(startNode, endNode);
		shortestPaths.add(getFullPath(0.05f,tmp)); //10 = speed
		startingNodes.add(startNode.getPos()); //add at same time so shortest and starting are in match
	}

	public Array<Node> getShortestPath(MapNode startNode){
		for(int i = 0; i < startingNodes.size;i++){
			Node node = startingNodes.get(i); //cant use foreach because i need index i
			if(startNode.getPos().equals(node)){
				return shortestPaths.get(i);
			}
		}
		calculateShortest(startNode); //now it only calculates if the path did not exist before
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

			

			current = nextStep;
			currentDistance = (float) start.getDistanceTo(current);
		}
		pixelPath.pop();
		pixelPath.add(goal);

		return pixelPath;
	}
	

}
