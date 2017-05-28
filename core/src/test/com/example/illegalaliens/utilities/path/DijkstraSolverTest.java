package com.example.illegalaliens.utilities.path;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.path.map.Map;
import com.example.illegalaliens.utilities.path.map.MapNode;

public class DijkstraSolverTest {
	private Map map;
	private Array<MapNode> mapNodes;
	private Array<MapNode> startingNodes;
	private MapNode endNode;
	private DijkstraSolver solver;
	
	@Before
	public void setUp(){
		map = new Map("AlphaMap");
		mapNodes = map.getMapNodes();
		startingNodes = map.getStartingNodes();
		endNode = mapNodes.peek();
		solver = new DijkstraSolver();
	}
	/**
	 * Test for dijkstraSolver on the map AlphaMap. Compares distance because there are several "correct" 
	 * paths that has equal length
	 * @throws Exception
	 */
	@Test
	public void solveAlphaMap() throws Exception{
		Array<Node> path = solver.solve(startingNodes.get(0), endNode, mapNodes);
		Array<Node> correctPath = new Array<Node>();
		
		correctPath.add(new Node(0,570));
		correctPath.add(new Node(130,570));
		correctPath.add(new Node(334,570));
		correctPath.add(new Node(508,570));
		correctPath.add(new Node(779,570));
		correctPath.add(new Node(779,454));
		correctPath.add(new Node(901,454));
		correctPath.add(new Node(901,394));
		correctPath.add(new Node(1280,394));
		
		float correctPathLength = 0;
		float dijkstraPathLength = 0;
		
		if(path.size == correctPath.size){
			
			for(int i = 0 ; i < correctPath.size - 1; i++){
				correctPathLength += correctPath.get(i).getDistanceTo(correctPath.get(i+1));
				dijkstraPathLength += path.get(i).getDistanceTo(path.get(i+1));
			}
		}
		assertEquals(correctPathLength , dijkstraPathLength , 0);
	}
	
	
}
