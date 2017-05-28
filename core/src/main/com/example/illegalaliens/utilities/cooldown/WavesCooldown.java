package com.example.illegalaliens.utilities.cooldown;

public interface WavesCooldown {
	int cdTurns();
	void afterCD(String hash);
}
