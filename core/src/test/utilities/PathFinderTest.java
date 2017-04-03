package test.utilities;

import org.junit.Test;
import utilities.PathFinder;

import static org.junit.Assert.*;

public class PathFinderTest {
    @Test
    public void getShortestPath() throws Exception {
        PathFinder pathFinder = PathFinder.getInstance();
        assertNotNull(pathFinder); //tests passed
//        assertNull(pathFinder); //tests failed

        //Just to give an example on how a jUnit test COULD work
        //Not a correct test of getShortestPath() at time of being
    }

}