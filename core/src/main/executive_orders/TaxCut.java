package executive_orders;

import buildings.WhiteHouse;
import politics.parties.Party;

public class TaxCut implements ExecutiveOrder {

	private int amount;
	private WhiteHouse whitehouse;
	private Party party;
	public TaxCut(WhiteHouse whitehouse, int amount, Party party){
		this.whitehouse = whitehouse;
		this.amount = amount;
		this.party= party;
	}

	@Override
	public void execute() {
		giveMoney(amount);
	}
	
	private void giveMoney(int amount){
		whitehouse.addMoney(amount);
	}

	@Override
	public String getDescription() {
		return "You instantly get "+amount+ "$. The next 5 turns everything is more expensive.";
	}

	@Override
	public Party getParty() {
		return party;
	}
}
