package towers;

import com.badlogic.gdx.utils.Array;

import buildings.BoardObject;
import buildings.Building;
import enemies.Enemy;
import politics.parties.Party;
import politics.parties.PartyFactory;

/**
 * Created by Emil on 2017-05-09.
 */
public class RiotShield extends Building {

    private static final String NAME = "RIOT SHIELD";
    private static String DESCRIPTION = "Pushes back nearby enemies";
    private static final int COST = 50;
    private static final float COOLDOWN = 120;
    private static final Party PARTY = PartyFactory.Democrat(6); // Just for now
    private static final int RADIUS = 100;
    private static final float SIZE = 50;

    private Array<Enemy> enemies;

    public RiotShield(int x, int y, float radius, float cooldown, int cost){
    	super(NAME, x, y, SIZE, radius, cooldown, cost, PARTY);
    }
    
    public RiotShield(int x, int y){
        this(x, y, RADIUS, COOLDOWN, COST);
    }


    @Override
    public String getDescription() {
        return DESCRIPTION;
    }


    public void updateEnemies(Array<Enemy> enemies){
        this.enemies = enemies;
    }

    @Override
    public void usePower() {
        if (enemies != null){
            for (Enemy e: enemies){
                e.setNodeArrayPos(e.getNodeArrayPos() - 500);
                if (e.getNodeArrayPos() <= 0)
                	e.setNodeArrayPos(0);
            }
        }
        super.getCooldownObject().setOnCooldown(true);
    }

	
	@Override
	public BoardObject clone(int x, int y) {
		return new RiotShield(x, y, getRadius(), getCooldownObject().getCooldownTime(), getCost());
	}
}
