package executive_orders;

import buildings.WhiteHouse;

public class TaxCut implements ExecutiveOrder {

	private int amount;
	private WhiteHouse whitehouse;
	public TaxCut(WhiteHouse whitehouse, int amount){
		this.whitehouse = whitehouse;
		this.amount = amount;
	}

	@Override
	public void execute() {
		giveMoney(amount);
	}
	
	private void giveMoney(int amount){
		whitehouse.addMoney(amount);
	}
}
