package utilities;

import com.badlogic.gdx.utils.Array;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for MapNode
 * @author Johan Svennungsson
 */
public class MapNodeTest {

    private MapNode mapNodeA;
    private MapNode mapNodeE;

    @Before
    public void setUp() throws Exception {
        mapNodeA = new MapNode("A", new Node(0, 570));
        mapNodeE = new MapNode("E", new Node(130, 570));
    }

    @Test
    public void getPathLength() throws Exception {
        assertEquals(mapNodeA.getPathLength(), Float.MAX_VALUE, 0.0);
    }

    @Test
    public void reset() throws Exception {
        mapNodeA.visit();
        mapNodeA.setPathLength(10);

        assertEquals(mapNodeA.hasBeenVisited(), true);
        assertEquals(mapNodeA.getPathLength(), 10, 0);

        mapNodeA.reset();

        assertEquals(mapNodeA.hasBeenVisited(), false);
        assertEquals(mapNodeA.getPathLength(), Float.MAX_VALUE, 0);
    }

    @Test
    public void visit() throws Exception {
        mapNodeA.visit();
        assertEquals(mapNodeA.hasBeenVisited(), true);
    }

    @Test
    public void hasBeenVisited() throws Exception {
        assertEquals(mapNodeA.hasBeenVisited(), false);
    }

    @Test
    public void setPathLength() throws Exception {
        mapNodeA.setPathLength(10);
        assertEquals(mapNodeA.getPathLength(), 10, 0);
    }

    @Test
    public void getPrevID() throws Exception {
        assertNull(mapNodeA.getPrevID());

        mapNodeE.setPrevID(mapNodeA.getID());
        assertEquals(mapNodeE.getPrevID(), "A");
    }

    @Test
    public void setPrevID() throws Exception {
        mapNodeE.setPrevID(mapNodeA.getID());
        assertEquals(mapNodeE.getPrevID(), "A");
    }

    @Test
    public void getID() throws Exception {
        assertEquals(mapNodeA.getID(), "A");
        assertEquals(mapNodeE.getID(), "E");
    }

    @Test
    public void getPos() throws Exception {
        assertEquals(mapNodeA.getPos().getX(), 0, 0);
        assertEquals(mapNodeA.getPos().getY(), 570, 0);
    }

    @Test
    public void getNeighbors() throws Exception {
        assertNotNull(mapNodeA.getNeighbors());
    }

    @Test
    public void setNeighbors() throws Exception {
        Array<String> neighbors = new Array<String>();
        neighbors.add("A");
        neighbors.add("F");
        neighbors.add("I");

        mapNodeE.setNeighbors(neighbors);

        assertEquals(mapNodeE.getNeighbors().size, 3);
        assertEquals(mapNodeE.getNeighbors().get(0), "A");
        assertEquals(mapNodeE.getNeighbors().get(1), "F");
        assertEquals(mapNodeE.getNeighbors().get(2), "I");
    }

    @Test
    public void addNeighbor() throws Exception {
        mapNodeA.addNeighbor(mapNodeE.getID());

        assertEquals(mapNodeA.getNeighbors().contains(mapNodeE.getID(), false), true);
    }

}