package com.example.illegalaliens.models.boardobjects.buildings;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.enemies.Enemy;
import com.example.illegalaliens.models.politics.parties.Party;
import com.example.illegalaliens.models.politics.parties.PartyFactory;

/**
 * Created by Emil on 2017-05-09.
 */
public class RiotShield extends Building {
    //Tower specific stats, name and description

    private static final String NAME = "RIOT SHIELD";
    private static String DESCRIPTION = "Pushes back nearby enemies";
    private static final int COST = 100;
    private static final float COOLDOWN = 70;
    private static final Party PARTY = PartyFactory.Democrat(6); // Just for now
    private static final int RADIUS = 70;
    private static final float SIZE = 20;

    private Array<Enemy> enemies;

    public RiotShield(int x, int y, float radius, float cooldown, float cost){
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
        if (enemies.size != 0){
        	super.getSpriteAdapter().rotateTowards(enemies.first().getPos(),-90); 
        }
    }

    @Override
    public void usePower() {
        if (enemies.size != 0){
            for (Enemy e: enemies){
                e.setNodeArrayPos(e.getNodeArrayPos() - 1000);
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
