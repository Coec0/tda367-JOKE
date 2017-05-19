package cooldown;

import com.badlogic.gdx.utils.Array;

public class WavesCDHandler {
	
private Array<TimeHelper> THs;
	
	public WavesCDHandler(){
		THs = new Array<TimeHelper>(false, 10);
	}

	public void addCooldownObject(WavesCooldown cooldown, int rounds){
		THs.add(new TimeHelper(cooldown, rounds));
	}
	public void addCooldownObject(WavesCooldown cooldown){
		THs.add(new TimeHelper(cooldown, cooldown.cdTurns()));
	}
	
	private void removeCooldown(TimeHelper TH){
		THs.removeValue(TH, false);
	}
	
	public void registerNewWave() {
		for(TimeHelper TH : THs){
			if(TH.removeRound()){
				TH.getWavesCooldown().afterCD();
				removeCooldown(TH);
			}
		}	
	}
	
	private class TimeHelper {
		int rounds;
		WavesCooldown cooldown;
		public TimeHelper(WavesCooldown cooldown, int rounds){
			this.rounds = rounds;
			this.cooldown = cooldown;
		}
		
		public boolean removeRound(){
			rounds--;
			return rounds <=0;
		}
		
		public WavesCooldown getWavesCooldown(){
			return cooldown;
		}
	}
	
}
