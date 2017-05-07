package path;

import com.badlogic.gdx.utils.Array;

import map.MapNode;
import utilities.Node;
import utilities.Radar;

public final class PathFinder {
	

	private Array<Array<Node>> shortestPaths;
	private Array<MapNode> startingNodes;
	private Array<MapNode> allNodes;
	private Array<Node> roadNetwork;
	private Array<RoadSection> roadSections;
	private DijkstraSolver DSolver;
	private MapNode endNode;
	private Radar radar;
	private float roadRadius;

	public PathFinder(Array<MapNode> allNodes, MapNode endNode) {
		this.endNode = endNode;
		this.allNodes = allNodes;
		shortestPaths = new Array<Array<Node>>();
		startingNodes = new Array<MapNode>();
		DSolver = new DijkstraSolver(allNodes);
		roadSections = new Array<RoadSection>();
		radar = new Radar();
		roadRadius = 10; //tmp hardcoded roadradius
	}
	
	//public boolean canRemoveNeighbor()
	
	

	public boolean isOnRoad(Node center, float centerRadius){
		
		if(roadNetwork == null){
			roadNetwork = new Array<Node>();
			roadNetwork = getRoadNetwork();
		}
		
		if(radar.scanNodeArray(roadNetwork, roadRadius, center, centerRadius).size != 0){
			
			return true;
		}
		return false;
	}
	
	
	public void removeNeighbor(RoadSection rs){
		Node tmp = rs.getStart();
		MapNode start = findMapNode(tmp);
		MapNode end = findMapNode(rs.getEnd());
		
		start.removeNeighbor(end.getID());
		end.removeNeighbor(start.getID());
	}
	
	
	public RoadSection findRoadSection(Node node){
		for(RoadSection rs : roadSections){
			Array<Node> nodes = radar.scanNodeArray(rs.getPixelWalk(), roadRadius , node, 1);
			if(nodes.size != 0){
					System.out.println("kaffe");
						return rs;
				
			}
		}
		return null;
	}
	
	
	private MapNode findMapNode(Node node){
		for(MapNode mn : allNodes){
			if(mn.getPos().equals(node)){
				return mn;
			}
		}
		return null;
	}
	
	private MapNode findMapNode(String ID){
		for(MapNode MN : allNodes){
			if(MN.getID().equals(ID)){
				return MN;
			}
		}
		return null;
	}
	
	public Array<Node> getRoadNetwork(){
		Array<Node> roadNetwork = new Array<Node>();
		RoadSection roadSection;
		for(int i = 0; i < allNodes.size;i++){
			MapNode mn = allNodes.get(i);
			
			
			for(String neighbor : mn.getNeighbors()){
				MapNode tmp  = findMapNode(neighbor);
				
				if(roadSections.size == 0){
					roadSection = new RoadSection(mn.getPos(), tmp.getPos(),1);
					roadSections.add(roadSection);
					roadNetwork.addAll(roadSection.getPixelWalk());
				}else{
					for(RoadSection rs : roadSections){
						if( !(rs.isStartOrEnd(mn.getPos()) && rs.isStartOrEnd(tmp.getPos()))){
							
							roadSection = new RoadSection(mn.getPos(), tmp.getPos(),1);
							roadSections.add(roadSection);
							roadNetwork.addAll(roadSection.getPixelWalk());
							break;
						}
					}
				}
				
			}
		}
		return roadNetwork;
	}
	
	public void reCalculateShortest(){
		Array<MapNode> tmpStarting = new Array<MapNode>();
		tmpStarting.addAll(startingNodes);
		shortestPaths.clear();
		startingNodes.clear();
		for(MapNode mn : tmpStarting){
			calculateShortest(mn);
		}
		
	}
	
	public void calculateShortest(MapNode startNode){
		Array<Node> tmp = DSolver.solve(startNode, endNode);
		shortestPaths.add(getFullPath(0.05f,tmp)); //10 = speed
		startingNodes.add(startNode); //add at same time so shortest and starting are in match
	}

	public Array<Node> getShortestPath(MapNode startNode){
		for(int i = 0; i < startingNodes.size;i++){
			Node node = startingNodes.get(i).getPos(); //cant use foreach because i need index i
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
		Array<Node> pixelPath;
		
		Node start;
		Node goal;
		RoadSection roadSection;
		
		for (int i = 0; i < pathNodes.size - 1; i++) { 
			start = pathNodes.get(i);
			goal = pathNodes.get(i+1);
			roadSection = new RoadSection(start,goal,speed);
			pixelPath = roadSection.calcPixelWalk(start, goal, speed);
			
			for (Node pixel : pixelPath) {
				fullPath.add(pixel);
			}
		}
	
		return fullPath;
	}

}
