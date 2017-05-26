package com.example.illegalaliens.utilities.cooldown;

public interface WavesCooldown {
	public int cdTurns();
	public void afterCD(String hash);
}
