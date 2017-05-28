package com.example.illegalaliens.utilities.path.map;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.utilities.Node;

public class Map {
	
	private Texture texture;
	private MapParser mapParser;
	private Array<MapNode> mapNodes;
	private String mapName;

	public Map(String mapName) {
		this.mapName = mapName;
		mapParser = new MapParser(mapName);
		mapNodes = this.addMapNodes(mapParser);
	}

	public Map(String mapName, Texture texture) {
		this.texture = texture;
		this.mapName = mapName;
		mapParser = new MapParser(mapName);
		mapNodes = this.addMapNodes(mapParser);
	}

	/**
	 * Return the first four starting MapNodes
	 * @return array with four nodes
	 */
	public Array<MapNode> getStartingNodes(){
		Array<MapNode> startingNodes = new Array<MapNode>();
		
		if(mapName.equals("map1")){
			startingNodes.add(mapNodes.get(0));
			startingNodes.add(mapNodes.get(1));
			startingNodes.add(mapNodes.get(2));
			startingNodes.add(mapNodes.get(3));
		}else if(mapName.equals("map2")){
			startingNodes.add(mapNodes.get(0));
			startingNodes.add(mapNodes.get(1));
			startingNodes.add(mapNodes.get(2));
			startingNodes.add(mapNodes.get(3));
			startingNodes.add(mapNodes.get(4));
		}else if (mapName.equals("AlphaMap")){
			startingNodes.add(mapNodes.get(0));
			startingNodes.add(mapNodes.get(1));
			startingNodes.add(mapNodes.get(2));
			startingNodes.add(mapNodes.get(3));
		}
		
		return startingNodes;
	}

	/**
	 * Adds all MapNodes
	 * @return array of MapNodes
	 */
	public Array<MapNode> addMapNodes(MapParser mapParser) {
		Array<MapNode> mapNodes = new Array<MapNode>();

		while(mapParser.hasNextLine()) {
			MapNode mapNode = this.convertLineToMapNode(mapParser.getParsedLine());
			mapNodes.add(mapNode);
		}

		return mapNodes;
	}

	/**
	 * Converts a line from .txt file to a MapNode
	 * @return
	 */
	public MapNode convertLineToMapNode(Array<String> segments) {
		int x = Integer.parseInt(segments.get(1));
		int y = Integer.parseInt(segments.get(2));

		MapNode mapNode = new MapNode(segments.get(0) , new Node(x, y));
			
		for(int i = 3; i < segments.size; i++){
			mapNode.addNeighbor(segments.get(i));
		}

		return mapNode;
	}
	
	public Array<MapNode> getMapNodes(){
		return mapNodes;
	}
	
	public Texture getTexture(){
		return texture;
	}
}
