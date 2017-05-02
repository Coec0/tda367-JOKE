package buildings.towers;

import projectiles.MarineBullet;
import projectiles.Projectile;

/**
 * Created by Emil on 2017-05-02.
 */
public class Marine extends Tower {
    private static final int RADIUS = 10;
    private static final String NAME = "MARINE";
    private static final int COST = 100;
    private static final long COOLDOWN = 4000;

    public Marine(int x, int y){
        super(x, y, RADIUS, NAME, COST, COOLDOWN );
    }


    @Override
    public Projectile makeProjectile() {
        Projectile p = new MarineBullet(super.getTarget().getPos(), super.getPos());
        super.notifyObservers(p, "spawn");
        return p;    }
}
