package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import com.badlogic.gdx.graphics.Texture;

public class Map {
	
	private String currentMapName;
	private Texture map;
	private Scanner scanner;
	
	
	public Map(String mapName){
		setMap(mapName);
	}
	
	public boolean setMap(String mapName){ //return true if mapName is found and map was changed
		if(mapName.equals("AlphaMap")){
			currentMapName = mapName;
			map = new Texture("AlphaMap.png");
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
	
	private void getNodes(String mapName){
		if(mapName.equals("AlphaMap")){
			try {
				scanner = new Scanner(new File("AlphaMapNodes.txt"));
			} catch (FileNotFoundException e) {
				System.out.println("File not Found");
				e.printStackTrace();
				return;
			}
			
		}
		String line;
		while(scanner.hasNextLine()){
			line = scanner.nextLine();
			
		}
		
	}
}
