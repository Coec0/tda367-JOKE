package utilities;

import com.badlogic.gdx.utils.Array;
import org.junit.Test;

import static org.junit.Assert.*;


public class SpriteCollectorTest {

    @Test
    public void getInstance() throws Exception {
        SpriteCollector spriteCollector = SpriteCollector.getInstance();
        assertNotNull(spriteCollector);
    }

    @Test
    public void getSprites() throws Exception {
        Array<SpriteAdapter> sprites = new Array<SpriteAdapter>();
        assertNotNull(sprites);
    }

    @Test
    public void addSprite() throws Exception {
        Array<SpriteAdapter> sprites = new Array<SpriteAdapter>();
        SpriteAdapter spriteAdapter = new SpriteAdapter();

        sprites.add(spriteAdapter);

        assertTrue(sprites.size == 1);
        assertTrue(sprites.contains(spriteAdapter, false));
    }

    @Test
    public void removeSprite() throws Exception {
        Array<SpriteAdapter> sprites = new Array<SpriteAdapter>();
        SpriteAdapter spriteAdapter = new SpriteAdapter();

        sprites.add(spriteAdapter);
        sprites.removeValue(spriteAdapter, false);

        assertTrue(sprites.size == 0);
        assertFalse(sprites.contains(spriteAdapter, false));
    }

}