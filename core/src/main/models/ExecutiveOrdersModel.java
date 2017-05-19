package models;

import cooldown.WavesCDHandler;
import cooldown.WavesCooldown;
import executive_orders.CivilWar;
import executive_orders.ExecutiveOrder;
import executive_orders.DeclareWar;
import politics.parties.Party;
import politics.parties.PartyFactory;
import towers.BOPrototypes;

public class ExecutiveOrdersModel {

	private ExecutiveOrder CWR, CWD;
	private DeclareWar DW;
	private Party republican, democrat;
	private WavesCDHandler wcd;
	
	public ExecutiveOrdersModel(BuildingModel BM, WavesCDHandler wcd, BOPrototypes prots){
		this.wcd = wcd;
		republican = PartyFactory.Republican(0);
		democrat = PartyFactory.Democrat(0);
		CWD = new CivilWar(BM, republican, democrat);
		CWR = new CivilWar(BM, democrat, republican);
		DW = new DeclareWar(BM.getWhiteHouses().peek(), 30000, republican, prots);
		wcd.addCooldownObject(DW);
	}
	
	
	public void civilWarAgainstRepublicans(){
		CWR.execute();
	}
	
	public void civilWarAgainstDemocrats(){
		CWD.execute();
	}
}
