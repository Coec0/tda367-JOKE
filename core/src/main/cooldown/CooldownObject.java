package cooldown;

public class CooldownObject {
	private int time;
	private boolean onCooldown;
	private int cooldownTicks;
	
	public CooldownObject(int time, boolean onCooldown){
		this.setCooldownTime(time);
		this.setOnCooldown(false);
		this.setCooldownTicks(time);
	}
	
	public CooldownObject(int cooldownTicks){
		this(cooldownTicks, false);
	}

	public int getCooldownTime() {
		return time;
	}

	public void setCooldownTime(int cooldownTime) {
		this.time = cooldownTime;
	}

	public void increaseCooldownTime(int cooldownTime){
		setCooldownTime(getCooldownTime() + cooldownTime);
	}
	
	public void decreaseCooldownTime(int cooldownTime){
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
			setCooldownTicks(0);	
	}

	public int getCooldownTicks() {
		return cooldownTicks;
	}
	
	public void resetTicks(){
		setCooldownTicks(this.getCooldownTime());
	}

	/**
	 * Sets cooldownTicks. If under or equals 0, then this object is no longer on cooldown.
	 * @param cooldownTicks
	 */
	public void setCooldownTicks(int cooldownTicks) {
		this.cooldownTicks = cooldownTicks;
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
