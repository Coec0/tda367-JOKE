package map;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.utilities.path.map.Map;
import com.example.illegalaliens.utilities.path.map.MapNode;
import com.example.illegalaliens.utilities.path.map.MapParser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for Map
 * @author Johan Svennungsson
 */
public class MapTest {

	private Map map;

	@Before
	public void setUp() throws Exception {
		map = new Map("AlphaMap");
	}

	@Test
	public void getStartingNodes() throws Exception {
		assertEquals(map.getStartingNodes().get(0).getID(), "A");
		assertEquals(map.getStartingNodes().get(0).getPos().getX(), 0, 0);
		assertEquals(map.getStartingNodes().get(0).getPos().getY(), 570, 0);
		assertEquals(map.getStartingNodes().get(0).getNeighbors().get(0), "E");
	}

	@Test
	public void addMapNodes() throws Exception {
		map.addMapNodes(new MapParser("AlphaMap"));
		assertEquals(map.getMapNodes().size, 24);

		map.getMapNodes().clear();
		assertEquals(map.getMapNodes().size, 0);
	}

	@Test
	public void convertLineToMapNode() throws Exception {
		Array<String> segments = new Array<String>();
		segments.addAll("Y", "593", "1200", "E", "K");

		MapNode mapNode = map.convertLineToMapNode(segments);

		assertEquals(mapNode.getID(), "Y");
		assertEquals(mapNode.getPos().getX(), 593, 0);
		assertEquals(mapNode.getPos().getY(), 1200, 0);
		assertEquals(mapNode.getNeighbors().get(0), "E");
		assertEquals(mapNode.getNeighbors().get(1), "K");
		assertEquals(mapNode.getNeighbors().size, 2);
	}

	@Test
	public void getMapNodes() throws Exception {
		assertEquals(map.getMapNodes().size, 24);
	}

	@Test
	public void getTexture() throws Exception {
		//Cannot instantiate Texture due to Gdx.files.internal
		assertNull(map.getTexture());
	}

}