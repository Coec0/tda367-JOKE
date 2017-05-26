package enemies;

public interface EnemyObserver {
	void actOnEnemyChange(Enemy enemy, boolean remove);
}
