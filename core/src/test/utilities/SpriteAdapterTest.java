package utilities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for SpriteAdapter
 * @author Johan Svennungsson
 */
public class SpriteAdapterTest {

    private SpriteAdapter spriteAdapterA;
    private SpriteAdapter spriteAdapterB;

    @Before
    public void setUp() throws Exception {
        spriteAdapterA = new SpriteAdapter(100,100);
        spriteAdapterB = new SpriteAdapter(200,200);
    }

    @Test
    public void getId() throws Exception {
        //TODO: ID dynamically changes during runtime of test, hard to confirm
//        assertEquals(spriteAdapterA.getId(), 0);
//        assertEquals(spriteAdapterB.getId(), 1);
    }

    @Test
    public void rotateTowards() throws Exception {
        assertEquals(spriteAdapterA.getRotation(), 0, 0);

        // 45 + 360 = 405 degrees
        assertEquals(spriteAdapterA.rotateTowards(new Node(200,200)).getRotation(), 405, 0);
    }

    @Test
    public void rotateTowardsWithOffset() throws Exception {
        assertEquals(spriteAdapterA.getRotation(), 0, 0);

        // (45-90) = -45 degrees
        assertEquals(spriteAdapterA.rotateTowards(new Node(200,200), 90).getRotation(), -45, 0);
    }

    @Test
    public void getAngleTo() throws Exception {
        assertEquals(spriteAdapterA.getAngleTo(new Node(200,200)), 45, 0.0);
        assertEquals(spriteAdapterB.getAngleTo(new Node(100,100)), -135, 0.0);
    }

    @Test
    public void getX() throws Exception {
        assertEquals(spriteAdapterA.getX(), 100, 0);
        spriteAdapterA.setPosition(150, spriteAdapterA.getY());
        assertEquals(spriteAdapterA.getX(), 150, 0);
    }

    @Test
    public void getY() throws Exception {
        assertEquals(spriteAdapterA.getY(), 100, 0);
        spriteAdapterA.setPosition(spriteAdapterA.getX(), 150);
        assertEquals(spriteAdapterA.getY(), 150, 0);
    }

    @Test
    public void setPosition() throws Exception {
        spriteAdapterA.setPosition(150,150);
        assertEquals(spriteAdapterA.getX(), 150, 0);
        assertEquals(spriteAdapterA.getY(), 150, 0);
    }

    @Test
    public void setSize() throws Exception {
        assertEquals(spriteAdapterA.getWidth(), 0, 0);
        assertEquals(spriteAdapterA.getHeight(), 0, 0);

        spriteAdapterA.setSize(100, 100);

        assertEquals(spriteAdapterA.getWidth(), 100, 0);
        assertEquals(spriteAdapterA.getHeight(), 100, 0);
    }

    @Test
    public void setTexture() throws Exception {
        //TODO
    }

    @Test
    public void equals() throws Exception {
        //TODO
    }

}