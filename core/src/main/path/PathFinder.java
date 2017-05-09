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
	
	

	public PathFinder(Array<MapNode> allNodes, MapNode endNode,Array<MapNode> startingNodes) {
		this.startingNodes = startingNodes;
		this.endNode = endNode;
		this.allNodes = allNodes;
		shortestPaths = new Array<Array<Node>>();
		DSolver = new DijkstraSolver();
		roadSections = new Array<RoadSection>();
		radar = new Radar();
		roadRadius = 10; //tmp hardcoded roadradius
		calculateAllShortest();
	}
	
	//public boolean canRemoveNeighbor()
	
	

	public boolean isOnRoad(Node center, float centerRadius){
		
		if(roadNetwork == null){
			roadNetwork = new Array<Node>();
			roadNetwork = calcRoadNetwork(allNodes, roadSections);
		}
		
		if(radar.scanNodeArray(roadNetwork, roadRadius, center, centerRadius).size != 0){
			return true;
		}
		return false;
	}
	
	
	public void removeNeighbor(RoadSection rs){
		Node tmp = rs.getStart();
		MapNode start = findMapNode(tmp,allNodes);
		MapNode end = findMapNode(rs.getEnd(),allNodes);
		
		start.removeNeighbor(end.getID());
		end.removeNeighbor(start.getID());
	}
	
	public void removeNeighbor(RoadSection rs,Array<MapNode> allNodes){
		Node tmp = rs.getStart();
		MapNode start = findMapNode(tmp,allNodes);
		MapNode end = findMapNode(rs.getEnd(),allNodes);
		
		start.removeNeighbor(end.getID());
		end.removeNeighbor(start.getID());
	}
	
	
	public RoadSection findRoadSection(Node node){
		for(RoadSection rs : roadSections){
			Array<Node> nodes = radar.scanNodeArray(rs.getPixelWalk(), roadRadius , node, 1);
			if(nodes.size != 0){
					
						return rs;
				
			}
		}
		return null;
	}
	
	
	private MapNode findMapNode(Node node, Array<MapNode> allNodes){
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
	
	public Array<Node> calcAllPixelWalk(Array<RoadSection> roadSections){
		Array<Node> nodes = new Array<Node>();
		for(RoadSection section : roadSections){
			nodes.addAll(section.getPixelWalk());
		}
		return nodes;
	}
	
	public Array<Node> calcRoadNetwork(Array<MapNode> allNodes, Array<RoadSection> roadSections){
		Array<Node> roadNetwork = new Array<Node>();
		RoadSection roadSection;
		for(int i = 0; i < allNodes.size;i++){
			MapNode mn = allNodes.get(i);
			
			for(String neighbor : mn.getNeighbors()){
				MapNode mnNeighbor  = findMapNode(neighbor);
				roadSection = new RoadSection(mn.getPos(), mnNeighbor.getPos(),1);
				
				if(roadSections.size == 0){
					roadSections.add(roadSection);
				}else{
					for(RoadSection rs : roadSections){
						if(! rs.isEndsInRoadSection(mn.getPos(),mnNeighbor.getPos())){
							roadSections.add(roadSection);
							break;
						}
					}
				}
				
			}
		}
		roadNetwork.addAll(calcAllPixelWalk(roadSections));
		return roadNetwork;
	}
	
	public boolean canRemoveNeighbors(RoadSection rs){
		
		removeNeighbor(rs,allNodes);
		
		for(MapNode startNode : startingNodes){
			
			Array<Node> tmp = calculateShortest(startNode,allNodes);
			System.out.println("hello: " + tmp.size);
			if(tmp.size == 0){
				System.out.println("Cant remove");
				MapNode start = findMapNode(rs.getStart(),allNodes);
				MapNode end = findMapNode(rs.getEnd(),allNodes);
				start.addNeighbor(start.getLastRemovedNeighbor());
				end.addNeighbor(end.getLastRemovedNeighbor());
				return false;
			}
		}
		
		System.out.println("Can remove");
		return true;
	}
	
	public void addShortest(Array<Node> tmp , MapNode startNode){
		shortestPaths.add(getFullPath(0.05f,tmp)); //10 = speed
		startingNodes.add(startNode); //add at same time so shortest and starting are in match
	}
	
	public void calculateAllShortest(){
		Array<MapNode> tmpStarting = new Array<MapNode>();
		tmpStarting.addAll(startingNodes);
		
		shortestPaths.clear();
		startingNodes.clear();
		for(MapNode mn : tmpStarting){
			Array<Node> tmp = calculateShortest(mn,allNodes);
			addShortest(tmp,mn);
		}
		
	}
	
	public Array<Node> calculateShortest(MapNode startNode,Array<MapNode> allNodes){
		Array<Node> tmp = DSolver.solve(startNode, endNode,allNodes);
		return tmp;
	}

	public Array<Node> getShortestPath(MapNode startNode){
		Array<Node> shortestPath = new Array<Node>();
		for(int i = 0; i < startingNodes.size; i++){
			Node node = startingNodes.get(i).getPos(); //cant use foreach because i need index i
			if(startNode.getPos().equals(node)){
				shortestPath.addAll(shortestPaths.get(i));
				return shortestPath;
			}
		}
		 //now it only calculates if the path did not exist before
		return null;
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
