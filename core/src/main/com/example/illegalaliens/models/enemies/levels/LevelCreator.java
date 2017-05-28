package com.example.illegalaliens.models.enemies.levels;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.*;

/**
 * Created by Emil on 2017-05-21.
 */

/**
 * This class is used for hardcoding enemy levels and waves
 */
public class LevelCreator {

    //Array containing all the levels created in constructor
    private Array<Level> levels = new Array<Level>();


    private Array<LevelHelperObject> L1 = new Array<LevelHelperObject>();
    private Array<LevelHelperObject> L2 = new Array<LevelHelperObject>();
    private Array<LevelHelperObject> L3 = new Array<LevelHelperObject>();
    private Array<LevelHelperObject> L4 = new Array<LevelHelperObject>();
    private Array<LevelHelperObject> L5 = new Array<LevelHelperObject>();
    private Array<LevelHelperObject> L6 = new Array<LevelHelperObject>();


    //Aliens available to use
    private Alien alien = new Alien();
    private HighAlien highAlien = new HighAlien();
    private SneakyAlien sneakyAlien = new SneakyAlien();
    private AlienWithHelmet alienWithHelmet = new AlienWithHelmet();
    private ToughAlien toughAlien = new ToughAlien();

    public LevelCreator(){
        //level 1
        L1.add(new LevelHelperObject(alien, 6));
        //level 2
        L2.add(new LevelHelperObject(alien,10));
        L2.add(new LevelHelperObject(sneakyAlien,10));
        //level 3
        L3.add(new LevelHelperObject(sneakyAlien,6));
        L3.add(new LevelHelperObject(alien,6));
        L3.add(new LevelHelperObject(alienWithHelmet,6));

        //level 4
        L4.add(new LevelHelperObject(sneakyAlien,10));
        L4.add(new LevelHelperObject(alien,10));
        L4.add(new LevelHelperObject(alienWithHelmet,10));
        L4.add(new LevelHelperObject(highAlien,10));
        //level 5
        L5.add(new LevelHelperObject(sneakyAlien,10));
        L5.add(new LevelHelperObject(alien,10));
        L5.add(new LevelHelperObject(alienWithHelmet,10));
        L5.add(new LevelHelperObject(highAlien,10));
        L5.add(new LevelHelperObject(toughAlien,10));

        //level 6
        L6.add(new LevelHelperObject(sneakyAlien,10));
        L6.add(new LevelHelperObject(alien,10));
        L6.add(new LevelHelperObject(alienWithHelmet,10));
        L6.add(new LevelHelperObject(highAlien,10));
        L6.add(new LevelHelperObject(toughAlien,20));

        levels.add(new Level(4,L1,true));
        levels.add(new Level(4,L2,true));
        levels.add(new Level(4,L3,true));
        levels.add(new Level(4,L4,true));
        levels.add(new Level(4,L5,true));
        levels.add(new Level(4,L6,true));

    }


    public Level getLevel(int levelIndex){
    	if(levels.size <= levelIndex){
    		return getExtendedLevel(levelIndex);
    	}
        return levels.get(levelIndex);
    }
    
    public Array<Level> getAllLevels(){
        return levels;
    }
    
    private Level getExtendedLevel(int levelIndex){
    	Array<LevelHelperObject> extendedLevel = new Array<LevelHelperObject>();
    	extendedLevel.add(new LevelHelperObject(sneakyAlien,7 + levelIndex));
    	extendedLevel.add(new LevelHelperObject(alienWithHelmet,7 + levelIndex));
    	extendedLevel.add(new LevelHelperObject(highAlien,10 + levelIndex));
    	extendedLevel.add(new LevelHelperObject(toughAlien, 10 + levelIndex));
    	return new Level(3,extendedLevel,true);
    	
    }


}
