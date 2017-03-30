package towers;

import utilities.Node;

public class Tank extends Tower{

    private static final Node POS = new Node(0,0);
    private static final int RADIUS = 10;
    private static final String NAME = "TANK";
    private static final int COST = 100;
    private static final int DAMAGE = 10;

    public Tank(){
        super(POS, RADIUS, NAME, COST, DAMAGE);

    }

}
