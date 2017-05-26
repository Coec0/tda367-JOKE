package map;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.utilities.path.map.MapParser;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test class for MapParser.
 * @author Johan Svennungsson
 */
public class MapParserTest {

	private MapParser mapParser;

	@Before
	public void setUp() throws Exception {
		mapParser = new MapParser("AlphaMap");
	}

	@Test
	public void hasNextLine() throws Exception {
		//24 lines in AlphaMapNodes.txt
		for (int i = 0 ; i < 24; i++) {
			assertEquals(mapParser.hasNextLine(), true);
			mapParser.getParsedLine();
		}
		assertEquals(mapParser.hasNextLine(), false);
	}

	@Test
	public void getParsedLine() throws Exception {
		//A;0;570;E
		Array<String> parsedLine = mapParser.getParsedLine();
		assertEquals(parsedLine.get(0), "A");
		assertEquals(parsedLine.get(1), "0");
		assertEquals(parsedLine.get(2), "570");
		assertEquals(parsedLine.get(3), "E");
	}

}