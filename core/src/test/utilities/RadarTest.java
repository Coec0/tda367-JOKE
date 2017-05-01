package utilities;

import buildings.towers.Soldier;
import com.badlogic.gdx.utils.Array;
import enemies.Alien;
import enemies.Enemy;
import models.AlienModel;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Johan Svennungsson
 */
public class RadarTest {

    private AlienModel alienModel = new AlienModel();
    private Radar radar = new Radar(alienModel);
    private Soldier soldier = new Soldier(100, 100);
    private Alien alien = new Alien();

    @Test
    public void scan() throws Exception {
        alien.setPos(new Node(150,150));
        alienModel.addAlien(alien);

        //Distance between soldier and Alien is 70.71, but the Alien's radius is 10. 70.71 - 10 = 60.61
        float failRadius = 60;
        float successRadius = 61;

        Array<Enemy> aliensNotFound = radar.scan(soldier.getPos(), failRadius);
        Array<Enemy> aliensFound = radar.scan(soldier.getPos(), successRadius);

        assertEquals(aliensNotFound.size, 0);
        assertFalse(aliensNotFound.contains(alien, false));

        assertEquals(aliensFound.get(0), alien);
        assertTrue(aliensFound.contains(alien, false));
    }

    @Test
    public void scanNegative() throws Exception {
        alien.setPos(new Node(-10, -10));
        alienModel.addAlien(alien);

        //Distance between soldier and Alien is 155.56, but the Alien's radius is 10. 155.56 - 10 = 145.56
        float failRadius = 145;
        float successRadius = 146;

        Array<Enemy> aliensNotFound = radar.scan(soldier.getPos(), failRadius);
        Array<Enemy> aliensFound = radar.scan(soldier.getPos(), successRadius);

        assertEquals(aliensNotFound.size, 0);
        assertFalse(aliensNotFound.contains(alien, false));

        assertEquals(aliensFound.get(0), alien);
        assertTrue(aliensFound.contains(alien, false));
    }

    @Test
    public void isEnemyWithinRadius() throws Exception {
        alien.setPos(new Node(150,150));
        alienModel.addAlien(alien);

        float distance = (float) soldier.getPos().getDistanceTo(alien.getPos());

        assertEquals(distance, 70.71, 0.01);
    }

}