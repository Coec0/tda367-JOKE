package path;

import org.junit.Test;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.Radar;
import com.example.illegalaliens.utilities.path.DijkstraSolver;
import com.example.illegalaliens.utilities.path.RoadManager;
import com.example.illegalaliens.utilities.path.RoadSection;
import com.example.illegalaliens.utilities.path.map.Map;
import com.example.illegalaliens.utilities.path.map.MapNode;

import static org.junit.Assert.*;

import org.junit.Before;

public class PathFinderTest {
	private Array<Array<Node>> shortestPaths;
	private Array<MapNode> startingNodes;
	private Array<MapNode> allNodes;
	private Array<Node> roadNetwork;
	private Array<RoadSection> roadSections;
	private DijkstraSolver DSolver;
	private MapNode endNode;
	private Radar radar;
	private float roadRadius;
	private Map map;
	private RoadManager finder;
	
	@Before
	public void setUp(){
		map = new Map("AlphaMap");
		startingNodes = map.getStartingNodes();
		allNodes = map.getMapNodes();
		endNode = allNodes.peek();
		radar = new Radar();
		shortestPaths = new Array<Array<Node>>();
		roadSections = new Array<RoadSection>();
		DSolver = new DijkstraSolver();
		roadRadius = 4;
		finder = new RoadManager(allNodes,endNode,startingNodes, radar);
	}
	
	@Test
	public void isOnRoad(){
		Node notOnRoad = new Node(10000,10000); //outside of screen. Cant be on road
		Node onRoad = allNodes.get(0).getPos();  //Has to be on the road
		assertEquals(finder.isOnRoad(notOnRoad, 1) , false);
		assertEquals(finder.isOnRoad(onRoad, 1) , true);
	}
	
	@Test
	public void calcRoadNetwork(){
		Node notInNetwork = new Node(10000,10000); //outside of screen. Cant in network
		Node inNetwork = new Node(100,570); //located inbetween MapNode A and E, is on roadnetwork
		Array<Node> network = finder.calcRoadNetwork(allNodes, roadSections);
		assertEquals(network.contains(notInNetwork, false) , false );
		assertEquals(network.contains(inNetwork, false) , true);
	}
	
	@Test
	public void removeNeighbor(){
		finder.calcRoadNetwork(allNodes, roadSections);
		RoadSection section = roadSections.get(10);
		Array<String> startBefore = new Array<String>();
		Array<String> endBefore = new Array<String>();
		
		for(String string : finder.findMapNode(section.getStart(),allNodes).getNeighbors()){
			startBefore.add(string);
		}
		
		for(String string : finder.findMapNode(section.getEnd(),allNodes).getNeighbors()){
			endBefore.add(string);
		}
		
		finder.removeNeighbor(section,allNodes);
		
		Array<String> startAfter = finder.findMapNode(section.getStart(), allNodes).getNeighbors();
		Array<String> endAfter = finder.findMapNode(section.getEnd(), allNodes).getNeighbors();
			
		assertNotEquals(startAfter , startBefore);
		assertNotEquals(endAfter , endBefore);
	}
	
	@Test
	public void canRemoveNeighbors(){
		//finder.calcRoadNetwork(allNodes, roadSections);
		RoadSection canRemove = new RoadSection(new Node(130,570), new Node(334,570) , 1); //between mapnode E and I. Can Remove
		RoadSection canNotRemove = new RoadSection(new Node(0,570),new Node(130,570),1); //can Not be able to remove between A and E
		
		assertEquals(finder.canRemoveNeighbors(canRemove) , true);
		assertEquals(finder.canRemoveNeighbors(canNotRemove) , false);
	}
	
	@Test
	public void getFullPath(){
		Array<Node> shortest = DSolver.solve(startingNodes.get(0), endNode,allNodes);
		Array<Node> fullPath;
		
		Node onPath = new Node(1000,394); //node in between V and Y;
		Node notOnPath = new Node(10000,10000); //not on screen
		
		fullPath = finder.getFullPath(1, shortest);
		
		assertEquals(fullPath.contains(onPath, false) , true);
		assertEquals(fullPath.contains(notOnPath, false), false);
		
		
	}
	
}