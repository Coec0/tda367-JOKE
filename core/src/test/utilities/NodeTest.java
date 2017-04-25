package utilities;

import org.junit.Test;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * Test class for Node
 * @author Johan Svennungsson
 */
public class NodeTest {

    @Test
    public void getDeltaX() throws Exception {
        Node node = new Node(100,0);
        Node other = new Node(0,100);

        assertEquals(node.getDeltaX(other), 100, 0);
    }

    @Test
    public void getDeltaY() throws Exception {
        Node node = new Node(0,0);
        Node other = new Node(100,150);

        assertEquals(node.getDeltaY(other), 150, 0);
    }

    @Test
    public void getLength() throws Exception {
        Node node = new Node(100,200);
        float x = node.getX();
        float y = node.getY();

        double testLength = Math.sqrt(x * x + y * y);
        assertEquals(node.getLength(), testLength, 0);
    }

    @Test
    public void getDistanceTo() throws Exception {
        Node start = new Node(200,200);
        Node goal = new Node(100,100);
        float dx = start.getDeltaX(goal);
        float dy = start.getDeltaY(goal);

        double testDistance = Math.sqrt(dx*dx + dy*dy);

        assertEquals(start.getDistanceTo(goal), testDistance, 0);
    }

    @Test
    public void getAsNormalizedNode() throws Exception {
        Node node = new Node(100, 100);
        Node another = new Node(200,200);

        float startDistance = (float) node.getDistanceTo(another);
        float dx = node.getDeltaX(another);
        float dy = node.getDeltaY(another);

        float normalizedX = dx/startDistance;
        float normalizedY = dy/startDistance;

        assertEquals(node.getAsNormalizedNode(another).getX(), normalizedX, 0);
        assertEquals(node.getAsNormalizedNode(another).getY(), normalizedY, 0);
    }

    @Test
    public void setCord() throws Exception {
        Node node = new Node(100, 100);
        node.setCord(150,200);

        assertEquals(node.getY(), 200, 0);
        assertEquals(node.getX(), 150, 0);
    }

}