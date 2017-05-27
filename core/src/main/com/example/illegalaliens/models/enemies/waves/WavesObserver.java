package com.example.illegalaliens.models.enemies.waves;

public interface WavesObserver {
	void actOnWavesChange(int wave, boolean finished);
}
