package com.example.illegalaliens.utilities.cooldown;

public class CooldownObject {
	private float time;
	private boolean onCooldown;
	private float cooldownTicks;
	
	public CooldownObject(float time, boolean onCooldown){
		this.setCooldownTime(time);
		this.setOnCooldown(false);
		this.setCooldownTicks(time);
	}
	
	public CooldownObject(float cooldownTicks){
		this(cooldownTicks, false);
	}

	public float getCooldownTime() {
		return time;
	}

	public void setCooldownTime(float cooldownTime) {
		this.time = cooldownTime;
	}

	public void increaseCooldownTime(float cooldownTime){
		setCooldownTime(getCooldownTime() + cooldownTime);
	}
	
	public void decreaseCooldownTime(float cooldownTime){
		setCooldownTime(getCooldownTime() - cooldownTime);
	}
	
	public boolean isOnCooldown() {
		return onCooldown;
	}

	/**
	 * Sets if this object is on cooldown. If set false, cooldownTicks will be set to 0.
	 * If true, it will be reset (set to time).
	 * @param onCooldown
	 */
	public void setOnCooldown(boolean onCooldown) {
		this.onCooldown = onCooldown;
		if(onCooldown)
			resetTicks();
		else
			setCDTicks(0);	
	}

	public float getCooldownTicks() {
		return cooldownTicks;
	}
	
	public void resetTicks(){
		setCooldownTicks(this.getCooldownTime());
	}

	private void setCDTicks(float cooldownTicks){
		this.cooldownTicks = cooldownTicks;
	}
	
	/**
	 * Sets cooldownTicks. If under or equals 0, then this object is no longer on cooldown.
	 * @param cooldownTicks
	 */
	public void setCooldownTicks(float cooldownTicks) {
		setCDTicks(cooldownTicks);
		if(this.cooldownTicks<=0)
			this.setOnCooldown(false);
	}
	
	/**
	 * Removes one cooldownTick. If cooldownTick reaches 0 or below, then this object is no longer on cooldown.
	 */
	public void tick(){
		setCooldownTicks(getCooldownTicks()-1);
	}
	
	
}
