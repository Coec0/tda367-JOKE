package com.example.illegalaliens.models.executive_orders;

import com.example.illegalaliens.models.AlienModel;
import com.example.illegalaliens.models.politics.parties.Party;

public class OpenBorders implements ExecutiveOrder {

	private AlienModel AM;
	private Party party;
	public OpenBorders(AlienModel AM, Party party){
		this.AM  = AM;
		this.party = party;
	}
	
	
	@Override
	public void execute() {
		AM.openBorders();
	}

	@Override
	public String getDescription() {
		return "Increase the amount of Aliens this wave";
	}

	@Override
	public Party getParty() {
		return party;
	}

}
