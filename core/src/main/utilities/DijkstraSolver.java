package utilities;

import com.badlogic.gdx.utils.Array;

public class DijkstraSolver {
	private Array<DijkstraNode> allNodes;
	private DijkstraNode vertex;
	
	public DijkstraSolver(Array<DijkstraNode> allNodes, DijkstraNode vertex){
		this.allNodes = allNodes;
		this.vertex = vertex;
	}
	
	
}
