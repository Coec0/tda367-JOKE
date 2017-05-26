package com.example.illegalaliens.models.executive_orders;

import com.example.illegalaliens.models.boardobjects.WhiteHouse;
import com.example.illegalaliens.models.boardobjects.towers.BOPrototypes;
import com.example.illegalaliens.models.politics.parties.Party;
import com.example.illegalaliens.utilities.cooldown.WavesCooldown;

public class TowerChanger implements ExecutiveOrder, WavesCooldown {
	private BOPrototypes prots;
	private float costChange;
	private float damageChange;
	private Party party;
	private WhiteHouse whitehouse;
	
	public TowerChanger(WhiteHouse whitehouse, BOPrototypes prots, float costChange, float damageChange, Party party){
		this.prots = prots;
		this.costChange = costChange;
		this.damageChange = damageChange;
		this.party = party;
		this.whitehouse = whitehouse;
	}
	
	@Override
	public void execute() {
		prots.changeCost(costChange);
		prots.changeDamage(damageChange);
		whitehouse.voteParty(party);
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
	public void afterCD(String hash) {
		prots.revertCost(costChange);
		prots.revertDamage(damageChange);
	}

}
