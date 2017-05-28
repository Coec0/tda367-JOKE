package com.example.illegalaliens.utilities;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.utilities.IAAdapter;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class DrawablesCollectorTest {

    private DrawablesCollector drawablesCollector;
    private IAAdapter spriteAdapter;
    private Stage stage;

    @Before
    public void setUp() throws Exception {
        drawablesCollector = new DrawablesCollector();
        spriteAdapter = new IAAdapter();
//        stage = new Stage();
    }

    @Test
    public void getInstance() throws Exception {
        assertNotNull(drawablesCollector);
    }

    @Test
    public void getSprites() throws Exception {
        assertNotNull(drawablesCollector.getSprites());
    }

    @Test
    public void addSprite() throws Exception {
        drawablesCollector.addSprite(spriteAdapter);

        assertEquals(drawablesCollector.getSprites().size, 1);
        assertEquals(drawablesCollector.getSprites().contains(spriteAdapter, false), true);
    }

    @Test
    public void removeSprite() throws Exception {
        drawablesCollector.removeSprite(spriteAdapter);

        assertEquals(drawablesCollector.getSprites().size, 0);
        assertEquals(drawablesCollector.getSprites().contains(spriteAdapter, false), false);
    }

    @Test
    public void drawSprites() throws Exception {
        //TODO
    }

    @Test
    public void addStage() throws Exception {
        drawablesCollector.addStage(stage);

        assertEquals(drawablesCollector.getStages().size, 1);
        assertEquals(drawablesCollector.getStages().contains(stage, false), true);
    }

    @Test
    public void removeStage() throws Exception {
        drawablesCollector.removeStage(stage);

        assertEquals(drawablesCollector.getStages().size, 0);
        assertEquals(drawablesCollector.getStages().contains(stage, false), false);
    }

    @Test
    public void getStages() throws Exception {
        assertNotNull(drawablesCollector.getStages());
    }

    @Test
    public void drawStages() throws Exception {
        //TODO
    }

    @Test
    public void drawAll() throws Exception {
        //TODO
    }

}