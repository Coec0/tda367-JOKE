package buildings.towers;

import buildings.Building;
import politics.parties.Democrat;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-09.
 */
public class RiotShield extends Building implements Democrat {

    private static final int RADIUS = 500;
    private static final String NAME = "RIOT SHIELD";
    private static final int COST = 50;
    private static final int COOLDOWN = 10;
    private int votes = 3; // Just for now
    private static String DESCRIPTION = "Pushes back nearby enemies";
    private static final float SIZE = 50;
    private static final float DAMAGE = 40;
    private static final float SPEED = 10;

    public RiotShield(int x, int y){
        super(NAME, x, y, SIZE, RADIUS);
    }

    @Override
    public String getDescription() {
        return null;
    }


    @Override
    public int getVotes() {
        return 0;
    }

    @Override
    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public void usePower() {
        
    }
}
