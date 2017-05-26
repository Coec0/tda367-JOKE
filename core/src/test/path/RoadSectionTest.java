package path;

import org.junit.Before;
import org.junit.Test;

import com.example.illegalaliens.utilities.Node;
import com.example.illegalaliens.utilities.path.RoadSection;

import static org.junit.Assert.*;

/**
 * Test class for RoadSection
 * @author Johan Svennungsson
 */
public class RoadSectionTest {

	private Node nodeA;
	private Node nodeE;
	private RoadSection roadSection;

	@Before
	public void setUp() throws Exception {
		nodeA = new Node(0, 570);
		nodeE = new Node(130,570);
		roadSection = new RoadSection(nodeA, nodeE, 1);
	}

	@Test
	public void calcPixelWalk() throws Exception {
		assertEquals(roadSection.calcPixelWalk(nodeA, nodeE, 1).size, 131);
		assertEquals(roadSection.calcPixelWalk(nodeA, nodeE, 2).size, 66);
	}

	@Test
	public void isStartOrEnd() throws Exception {
		Node start = new Node(0,570);
		Node end = new Node(130,570);
		assertEquals(roadSection.isStartOrEnd(start), true);
		assertEquals(roadSection.isStartOrEnd(end), true);

		Node notStart = new Node (1, 570);
		Node notEnd = new Node(129,570);
		assertEquals(roadSection.isStartOrEnd(notStart), false);
		assertEquals(roadSection.isStartOrEnd(notEnd), false);
	}

	@Test
	public void getSpeed() throws Exception {
		assertEquals(roadSection.getSpeed(), 1, 0);
	}

	@Test
	public void setSpeed() throws Exception {
		roadSection.setSpeed(2);
		assertEquals(roadSection.getPixelWalk().size, 66);
	}

	@Test
	public void getStart() throws Exception {
		assertEquals(roadSection.getStart(), nodeA);
	}

	@Test
	public void getEnd() throws Exception {
		assertEquals(roadSection.getEnd(), nodeE);
	}

	@Test
	public void getPixelWalk() throws Exception {
		assertEquals(roadSection.getPixelWalk().size, 131);
	}

	@Test
	public void isEndsInRoadSection() throws Exception {
		Node notStart = new Node(1, 570);
		Node notEnd = new Node(129, 570);
		assertEquals(roadSection.isEndsInRoadSection(nodeA, nodeE), true);
		assertEquals(roadSection.isEndsInRoadSection(notStart, nodeE), false);
		assertEquals(roadSection.isEndsInRoadSection(nodeA, notEnd), false);
	}

}