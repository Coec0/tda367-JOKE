package towers;

public class Soldier extends Tower {
    private static final int RADIUS = 100;
    private static final String NAME = "SOLDIER";
    private static final int COST = 50;
    private static final int DAMAGE = 5;

    public Soldier(int x, int y){
        super(x, y, RADIUS, NAME, COST, DAMAGE);
    }

}
