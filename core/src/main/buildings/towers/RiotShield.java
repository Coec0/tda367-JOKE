package buildings.towers;

import buildings.Building;
import com.badlogic.gdx.utils.Array;
import enemies.Enemy;
import politics.parties.Democrat;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-09.
 */
public class RiotShield extends Building implements Democrat {

    private static final String NAME = "RIOT SHIELD";
    private static String DESCRIPTION = "Pushes back nearby enemies";
    private static final int RADIUS = 500;
    private int votes = 3; // Just for now
    private static final float SIZE = 50;
    private int cooldown;
    private int cooldownTimer;

    private Array<Enemy> enemies;

    public RiotShield(int x, int y){
        super(NAME, x, y, SIZE, RADIUS);
        this.cooldown = 20;
        cooldownTimer = cooldown;
    }

    public boolean isInCooldown(){
        cooldownTimer--;
        if(cooldownTimer <= 0){
            cooldownTimer = cooldown;
            return false;
        }
        return true;
    }


    @Override
    public String getDescription() {
        return DESCRIPTION;
    }


    @Override
    public int getVotes() {
        return votes;
    }

    @Override
    public void setVotes(int votes) {
        this.votes = votes;
    }

    public void updateEnemies(Array<Enemy> enemies){
        this.enemies = enemies;
    }

    @Override
    public void usePower() {
        if (enemies != null){
            for (Enemy e: enemies){
                e.setNodeArrayPos(e.getNodeArrayPos() - 1000);
            }
        }
    }
}
