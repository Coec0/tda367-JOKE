package executive_orders;

import cooldown.WavesCooldown;
import politics.parties.Party;
import towers.BOPrototypes;

public class TowerChanger implements ExecutiveOrder, WavesCooldown {
	private BOPrototypes prots;
	private float costChange;
	private float damageChange;
	private Party party;
	
	public TowerChanger(BOPrototypes prots, float costChange, float damageChange, Party party){
		this.prots = prots;
		this.costChange = costChange;
		this.damageChange = damageChange;
		this.party = party;
	}
	
	@Override
	public void execute() {
		prots.changeCost(costChange);
		prots.changeDamage(damageChange);
	}

	@Override
	public String getDescription() {
		return "Buildings and towers changes it cost by "+costChange*100+"% and damage by "
				+ damageChange*100+"% for the next "+cdTurns()+" turns.";
	}

	@Override
	public Party getParty() {
		return party;
	}

	@Override
	public int cdTurns() {
		return 5;
	}

	@Override
	public void afterCD() {
		prots.revertCost(costChange);
		prots.revertDamage(damageChange);
	}

}
