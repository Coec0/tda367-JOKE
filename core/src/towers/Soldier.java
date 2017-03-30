package towers;

import utilities.Node;

import java.awt.*;

public class Soldier extends Tower {
    private static final Node POS = new Node(0,0);
    private static final int RADIUS = 5;
    private static final String NAME = "SOLDIER";
    private static final int COST = 50;
    private static final int DAMAGE = 5;

    public Soldier(){
        super(POS, RADIUS, NAME, COST, DAMAGE);
    }

}
