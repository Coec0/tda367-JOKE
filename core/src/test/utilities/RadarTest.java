package utilities;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.boardobjects.towers.Soldier;
import com.example.illegalaliens.models.enemies.Alien;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.utilities.Radar;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Johan Svennungsson
 */
public class RadarTest {

    private Array<Enemy> enemyArray;
    private Radar radar;
    private Soldier soldier;
    private Alien alien;

    @Before
    public void setUp() throws Exception {
        enemyArray = new Array<Enemy>();
        radar = new Radar();
        soldier = new Soldier(100,100);
        alien = new Alien();

        enemyArray.add(alien);
    }
    @Test
    public void scan() throws Exception {
        alien.setPos(150,150);
        //Distance between soldier and Alien is 70.71, but the Alien's radius is 10. 70.71 - 10 = 60.61
        float failRadius = 60;
        float successRadius = 61;

        Array<Enemy> aliensNotFound = radar.scan(soldier.getPos(), failRadius, enemyArray);
        Array<Enemy> aliensFound = radar.scan(soldier.getPos(), successRadius, enemyArray);

        assertEquals(aliensNotFound.size, 0);
        assertFalse(aliensNotFound.contains(alien, false));

        assertEquals(aliensFound.get(0), alien);
        assertTrue(aliensFound.contains(alien, false));
    }

    @Test
    public void scanNegative() throws Exception {
        alien.setPos(-10,-10);

        //Distance between soldier and Alien is 155.56, but the Alien's radius is 10. 155.56 - 10 = 145.56
        float failRadius = 145;
        float successRadius = 146;

        Array<Enemy> aliensNotFound = radar.scan(soldier.getPos(), failRadius, enemyArray);
        Array<Enemy> aliensFound = radar.scan(soldier.getPos(), successRadius, enemyArray);

        assertEquals(aliensNotFound.size, 0);
        assertEquals(aliensNotFound.contains(alien, false), false);

        assertEquals(aliensFound.get(0), alien);
        assertEquals(aliensFound.contains(alien, false), true);
    }

    @Test
    public void isEnemyWithinRadius() throws Exception {
        alien.setPos(150,150);

        float distance = (float) soldier.getPos().getDistanceTo(alien.getPos());

        assertEquals(distance, 70.71, 0.01);
    }
}