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
	private Array<Node> mapNodes;
	
	public Map(String mapName){
		setMap(mapName);
	}
	
	public boolean setMap(String mapName){ //return true if mapName is found and map was changed
		if(mapName.equals("AlphaMap")){
			currentMapName = mapName;
			map = new Texture("AlphaMap.png");
			readNodes(mapName);
			return true;
		}
		return false;
	}
	
	public Texture getMap(){
		return map;
	}
	
	public String getMapName(){
		return currentMapName;
	}
	
	private void readNodes(String mapName){
		if(mapName.equals("AlphaMap")){
			try {
				scanner = new Scanner(new File("AlphaMapNodes.txt"));
			} catch (FileNotFoundException e) {
				System.out.println("File not Found");
				e.printStackTrace();
				return;
			}
			
		}
		
		mapNodes = new Array<Node>();
		//MapNode tmpNode;
		Node pos;
		String[] segments;
		while(scanner.hasNextLine()){
			segments = scanner.nextLine().split(";");
			pos = new Node(Integer.parseInt(segments[1]),Integer.parseInt(segments[2]));
			//tmpNode = new MapNode(segments[0],pos);
			mapNodes.add(pos);
			System.out.println(segments[0]);
		}
		
	}
	
	public Array<Node> getMapNodes(){
		return mapNodes;
	}
}
