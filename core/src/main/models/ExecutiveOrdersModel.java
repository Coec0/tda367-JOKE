package models;

import executive_orders.CivilWar;
import executive_orders.ExecutiveOrder;
import politics.parties.Party;
import politics.parties.PartyFactory;

public class ExecutiveOrdersModel {

	private ExecutiveOrder CWR, CWD;
	private Party republican, democrat;
	
	public ExecutiveOrdersModel(BuildingModel BM){
		republican = PartyFactory.Republican(0);
		democrat = PartyFactory.Democrat(0);
		CWD = new CivilWar(BM, republican, democrat);
		CWR = new CivilWar(BM, democrat, republican);
	}
	
	
	public void civilWarAgainstRepublicans(){
		CWR.execute();
	}
	
	public void civilWarAgainstDemocrats(){
		CWD.execute();
	}
}
