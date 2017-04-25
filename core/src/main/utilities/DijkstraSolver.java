package utilities;

import com.badlogic.gdx.utils.Array;

public class DijkstraSolver {
	private Array<MapNode> allNodes;
	private MapNode vertex;
	
	public DijkstraSolver(Array<MapNode> allNodes, MapNode vertex){
		this.allNodes = allNodes;
		this.vertex = vertex;
	}
	
	
}
