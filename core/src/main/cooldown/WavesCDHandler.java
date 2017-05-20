package cooldown;

import com.badlogic.gdx.utils.Array;

public class WavesCDHandler {
	
private Array<TimeHelper> THs;
	
	public WavesCDHandler(){
		THs = new Array<TimeHelper>(false, 10);
	}

	public void startCooldown(WavesCooldown cooldown, int rounds, String hash){
			THs.add(new TimeHelper(cooldown, rounds, hash));
	}
	public void startCooldown(WavesCooldown cooldown){
		startCooldown(cooldown, cooldown.cdTurns(), "");
	}
	
	
	private void removeCooldown(TimeHelper TH){
		THs.removeValue(TH, false);
	}
	
	public void registerNewWave() {
		Array<TimeHelper> gonnaRemove = new Array<TimeHelper>(false, THs.size);
	
		for(TimeHelper TH : THs){
			if(TH.removeRound()){
				TH.getWavesCooldown().afterCD(TH.getHash());
				gonnaRemove.add(TH);
			}
		}	
		for(TimeHelper TH : gonnaRemove){
			removeCooldown(TH);
		}
	}
	
	
	
	private class TimeHelper {
		private int rounds;
		private WavesCooldown cooldown;
		private String hash;
		public TimeHelper(WavesCooldown cooldown, int rounds, String hash){
			this.rounds = rounds;
			this.cooldown = cooldown;
			this.hash = hash;
		}
		
		public String getHash(){
			return hash;
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
