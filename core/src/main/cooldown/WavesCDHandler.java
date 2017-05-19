package cooldown;

import com.badlogic.gdx.utils.Array;

public class WavesCDHandler {
	
private Array<TimeHelper> THs;
	
	public WavesCDHandler(){
		THs = new Array<TimeHelper>(false, 10);
	}

	public void startCooldown(WavesCooldown cooldown, int rounds){
			THs.add(new TimeHelper(cooldown, rounds));
	}
	public void startCooldown(WavesCooldown cooldown){
		startCooldown(cooldown, cooldown.cdTurns());
	}
	
	
	private void removeCooldown(TimeHelper TH){
		THs.removeValue(TH, false);
	}
	
	public void registerNewWave() {
		Array<TimeHelper> gonnaRemove = new Array<TimeHelper>(false, THs.size);
	
		for(TimeHelper TH : THs){
			if(TH.removeRound()){
				TH.getWavesCooldown().afterCD();
				gonnaRemove.add(TH);
			}
		}	
		for(TimeHelper TH : gonnaRemove){
			removeCooldown(TH);
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
