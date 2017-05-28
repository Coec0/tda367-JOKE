package com.example.illegalaliens.utilities.path.map;

import com.badlogic.gdx.utils.Array;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MapParser {

    private Scanner scanner;
    private int i = 0;
    public MapParser(String mapName) {
        try {
            scanner = new Scanner(new File("maps/" + mapName + "Nodes.txt"));
            
        } catch (FileNotFoundException e) {
            System.out.println("File not Found");
            e.printStackTrace();
        }
    }

    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    /**
     * Parses a line from .txt-document
     * @return array
     */
    public Array<String> getParsedLine() {
        Array<String> arraySegments = new Array<String>();
        String[] segments = scanner.nextLine().split(";");
        System.out.println(i++ + "  :hej : " + segments[0]);
        for (String segment : segments) {
        	
            arraySegments.add(segment);
        }

        return arraySegments;
    }
}
