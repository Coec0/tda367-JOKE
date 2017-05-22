package executive_orders;

import models.AlienModel;
import politics.parties.Party;

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
