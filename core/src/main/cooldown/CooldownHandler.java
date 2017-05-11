package cooldown;

import com.badlogic.gdx.utils.Array;

import observers.UpdateObserver;

public class CooldownHandler implements UpdateObserver {
	
	private Array<CooldownObject> cooldowns;
	
	public CooldownHandler(){
		cooldowns = new Array<CooldownObject>(false, 10);
	}

	public void addCooldownObject(CooldownObject cooldown){
		cooldowns.add(cooldown);
	}
	
	public void removeCooldownObject(CooldownObject cooldown){
		cooldowns.removeValue(cooldown, false);
	}
	
	@Override
	public void update(float deltaTime) {
		for(CooldownObject cooldown : cooldowns){
			if(cooldown.isOnCooldown()){
				cooldown.tick();
			}
		}	
	}
}
