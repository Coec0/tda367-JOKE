package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Array;

public class Map {
	
	private String currentMapName;
	private Texture map;
	private Scanner scanner;
	private Array<MapNode> mapNodes;
	private Array<MapNode> startingNodes;
	
	public Map(String mapName){
		setMap(mapName);
	}
	
	public boolean setMap(String mapName){ //return true if mapName is found and map was changed
		if(mapName.equals("AlphaMap")){
			currentMapName = mapName;
			map = new Texture("AlphaMap.png");
			mapNodes = new Array<MapNode>();
			readNodes(mapName);
			setStartingNodes();
			return true;
		}
		return false;
	}
	
	public Array<MapNode> getStartingNodes(){
		return startingNodes;
	}
	
	public boolean setStartingNodes(){
		if(currentMapName.equals("AlphaMap")){
			startingNodes = new Array<MapNode>();
			startingNodes.add(mapNodes.get(0));
			startingNodes.add(mapNodes.get(1));
			startingNodes.add(mapNodes.get(2));
			startingNodes.add(mapNodes.get(3));
			return true;
		}
		return false;
	}
	
	
	
	private void readNodes(String mapName){
		setScanner(mapName);
		mapNodes.clear();
		MapNode mapNode;
		while(scanner.hasNextLine()){
			String[] segments = scanner.nextLine().split(";");
			
			int xCord = Integer.parseInt(segments[1]);
			int yCord = Integer.parseInt(segments[2]);
			
			mapNode = new MapNode(segments[0] , new Node(xCord,yCord));
			
			for(int i = 3; i < segments.length; i++){
				mapNode.addNeighbor(segments[i]);
				//System.out.println(mapNode.getID() +": "+ segments[i]);
			}
			mapNodes.add(mapNode);
		}
		
	}
	
	private void setScanner(String mapName){
		if(mapName.equals("AlphaMap")){
			try {
				scanner = new Scanner(new File("AlphaMapNodes.txt"));
			} catch (FileNotFoundException e) {
				System.out.println("File not Found");
				e.printStackTrace();
				return;
			}
		}
	}
	
	public Array<MapNode> getMapNodes(){
		return mapNodes;
	}
	
	public Texture getMap(){
		return map;
	}
	
	public String getMapName(){
		return currentMapName;
	}
}
