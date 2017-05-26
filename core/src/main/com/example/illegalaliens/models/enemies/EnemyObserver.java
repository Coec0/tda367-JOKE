package com.example.illegalaliens.models.enemies;

public interface EnemyObserver {
	void actOnEnemyChange(Enemy enemy, boolean remove);
}
