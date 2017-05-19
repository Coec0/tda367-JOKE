package executive_orders;

import buildings.WhiteHouse;
import cooldown.WavesCooldown;
import politics.parties.Party;
import towers.BOPrototypes;

public class DeclareWar implements ExecutiveOrder, WavesCooldown {

	private int amount;
	private WhiteHouse whitehouse;
	private Party party;
	private BOPrototypes prots;
	public DeclareWar(WhiteHouse whitehouse, int amount, Party party, BOPrototypes prots){
		this.whitehouse = whitehouse;
		this.amount = amount;
		this.party= party;
		this.prots = prots;
	}

	@Override
	public void execute() {
		giveMoney(amount);
	}
	
	private void giveMoney(int amount){
		whitehouse.addMoney(amount);
		prots.changeCost(1.5f);
	}

	@Override
	public String getDescription() {
		return "You instantly get "+amount+ "$. The next 5 turns everything is more expensive.";
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
		prots.revertCost(1.5f);
	}
}
