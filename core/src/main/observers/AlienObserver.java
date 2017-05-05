package observers;

import enemies.Enemy;

public interface AlienObserver {
	void actOnEnemyChange(Enemy enemy, boolean remove);
}
