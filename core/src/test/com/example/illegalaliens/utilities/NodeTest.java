package com.example.illegalaliens.utilities;

import org.junit.Before;
import org.junit.Test;

import com.example.illegalaliens.utilities.Node;

import static org.junit.Assert.*;

/**
 * Test class for Node
 * @author Johan Svennungsson
 */
public class NodeTest {

    private Node node;
    private Node other;

    @Before
    public void setUp() throws Exception {
        node = new Node(100,100);
        other = new Node(200,200);
    }

    @Test
    public void getDeltaX() throws Exception {
        Node another = new Node(-100,-100);
        assertEquals(node.getDeltaX(another), -200, 0);
    }

    @Test
    public void getDeltaY() throws Exception {
        Node other = new Node(100,150);

        assertEquals(node.getDeltaY(other), 50, 0);
    }

    @Test
    public void getLength() throws Exception {
        float x = node.getX();
        float y = node.getY();

        double testLength = Math.sqrt(x * x + y * y);
        assertEquals(node.getLength(), testLength, 0);
    }

    @Test
    public void getDistanceTo() throws Exception {
        float dx = node.getDeltaX(other);
        float dy = node.getDeltaY(other);

        double testDistance = Math.sqrt(dx*dx + dy*dy);

        assertEquals(node.getDistanceTo(other), testDistance, 0);
    }

    @Test
    public void getAsNormalizedNode() throws Exception {
        float startDistance = (float) node.getDistanceTo(other);
        float dx = node.getDeltaX(other);
        float dy = node.getDeltaY(other);

        float normalizedX = dx/startDistance;
        float normalizedY = dy/startDistance;

        assertEquals(node.getAsNormalizedNode(other).getX(), normalizedX, 0);
        assertEquals(node.getAsNormalizedNode(other).getY(), normalizedY, 0);
    }

    @Test
    public void setCord() throws Exception {
        node.setCord(150,200);

        assertEquals(node.getY(), 200, 0);
        assertEquals(node.getX(), 150, 0);
    }

}