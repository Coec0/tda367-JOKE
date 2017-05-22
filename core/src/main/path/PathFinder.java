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
	
	

	public PathFinder(Array<MapNode> allNodes, MapNode endNode,Array<MapNode> startingNodes,Radar radar) {
		this.startingNodes = startingNodes;
		this.endNode = endNode;
		this.allNodes = allNodes;
		shortestPaths = new Array<Array<Node>>();
		DSolver = new DijkstraSolver();
		roadSections = new Array<RoadSection>();
		this.radar = radar;
		roadRadius = 4;
		calculateAllShortest();
	}
	
	/**
	 * Checks if a node is on any roadsection
	 * @param center The node to see if its on a roadsection
	 * @param centerRadius	Radius/hitbox of centerNode. 
	 * @return true if on road.
	 */
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
	
	/**
	 * 
	 * @param node to be found in a roadsection
	 * @return	The first roadsection that contains node. 
	 */
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
	
	/**
	 * 
	 * @param allNodes. All the MapNodes that forms a network
	 * @param roadSections. All roadSections between all nodes. 
	 * @return Returns all the nodes between all the MapNodes.
	 */
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
	
	/**
	 * Checks if it is possible to remove neighbors from a roadsection
	 * @param rs. Roadsection to see if neighbors can be removed
	 * @return true if neighbors can be removed
	 */
	public boolean canRemoveNeighbors(RoadSection rs){
		
		removeNeighbor(rs,allNodes);
		
		for(MapNode startNode : startingNodes){
			
			Array<Node> tmp = calculateShortest(startNode,allNodes);
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
	
	/**
	 * Calculates the shortestpaths for all starting nodes
	 */
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
	
	/**
	 * 
	 * @param startNode Node for dijkstraSolver to start on
	 * @param allNodes	The net of MapNode that dijkstra will find path in.
	 * @return	Returns only the nodes that that forms the path. In correct order. 
	 */
	public Array<Node> calculateShortest(MapNode startNode,Array<MapNode> allNodes){
		Array<Node> tmp = DSolver.solve(startNode, endNode,allNodes);
		return tmp;
	}

	/**
	 * Returns the shortest path with startNode.
	 * @param startNode Node to find correct shortestPath
	 * @return	The shortestpath. If it cant be found returns null.
	 */
	public Array<Node> getShortestPath(MapNode startNode){
		Array<Node> shortestPath = new Array<Node>();
		for(int i = 0; i < startingNodes.size; i++){
			Node node = startingNodes.get(i).getPos(); 
			if(startNode.getPos().equals(node)){
				shortestPath.addAll(shortestPaths.get(i));
				return shortestPath;
			}
		}

		return null;
	}
		
	
	public void setAllNodes(Array<MapNode> allNodes){
		this.allNodes = allNodes;
	}
	


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
