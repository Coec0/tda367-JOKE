package com.example.illegalaliens.utilities;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.Radar;

/**
 * @author Johan Svennungsson
 */
public class RadarTest {

    private Array<Node> nodeArray;
    private Radar radar;
    private Node node1;
    private Node node2;

    @Before
    public void setUp() throws Exception {
        nodeArray = new Array<Node>();
        radar = new Radar();
        node1 = new Node(100,100);
        node2 = new Node(0,0);

        nodeArray.add(node2);
    }
    @Test
    public void scan() throws Exception {
        node2.setCord(150, 150);
        //Distance between node1 and node2 is 70.71, but the n radius is 10. 70.71 - 10 = 60.61
        float failRadius = 60;
        float successRadius = 61;

        Array<Node> nodesNotFound = radar.scan(node1, failRadius, nodeArray,10);
        Array<Node> nodesFound = radar.scan(node1, successRadius, nodeArray,10);

        assertEquals(nodesNotFound.size, 0);
        assertFalse(nodesNotFound.contains(node2, false));

        assertEquals(nodesFound.get(0), node2);
        assertTrue(nodesFound.contains(node2, false));
    }

    @Test
    public void scanNegative() throws Exception {
    	 node2.setCord(-10, -10);

        //Distance between node1 and node2 is 155.56, but the node2's radius is 10. 155.56 - 10 = 145.56
        float failRadius = 145;
        float successRadius = 146;

        Array<Node> nodesNotFound = radar.scan(node1, failRadius, nodeArray,10);
        Array<Node> nodesFound = radar.scan(node1, successRadius, nodeArray,10);

        assertEquals(nodesNotFound.size, 0);
        assertEquals(nodesNotFound.contains(node2, false), false);

        assertEquals(nodesFound.get(0), node2);
        assertEquals(nodesFound.contains(node2, false), true);
    }

    @Test
    public void isEnemyWithinRadius() throws Exception {
    	node2.setCord(150, 150);

        float distance = (float) node1.getDistanceTo(node2);

        assertEquals(distance, 70.71, 0.01);
    }
}