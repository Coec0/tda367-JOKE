package models;

import cooldown.WavesCDHandler;
import executive_orders.CivilWar;
import executive_orders.DeclareWar;
import executive_orders.TowerChanger;
import politics.parties.Party;
import politics.parties.PartyFactory;
import towers.BOPrototypes;

public class ExecutiveOrdersModel {

	private CivilWar CWR, CWD;
	private DeclareWar DW;
	private TowerChanger TC, OC;
	private Party republican, democrat;
	private WavesCDHandler wcd;

	public ExecutiveOrdersModel(BuildingModel BM, WavesCDHandler wcd, BOPrototypes prots){
		this.wcd = wcd;
		republican = PartyFactory.Republican(0);
		democrat = PartyFactory.Democrat(0);
		CWD = new CivilWar(BM, republican, democrat);
		CWR = new CivilWar(BM, democrat, republican);
		
		TC = new TowerChanger(BM.getWhiteHouses().peek(), prots, 0.5f, 0.5f , PartyFactory.Republican(100));
		OC = new TowerChanger(BM.getWhiteHouses().peek(), prots, 1.5f, 1.5f, PartyFactory.Democrat(100));
		DW = new DeclareWar(BM.getWhiteHouses().peek(), 30000, PartyFactory.Republican(30), prots);
	}
	
	
	
	public void declareWar(){
		DW.execute();
		wcd.startCooldown(DW);
	}
	
	public void civilWarAgainstRepublicans(){
		CWR.execute();
	}
	
	public void civilWarAgainstDemocrats(){
		CWD.execute();
	}
	
	public void taxCut(){
		TC.execute();
		wcd.startCooldown(TC);
	}
	
	public void obamaCare(){
		OC.execute();
		wcd.startCooldown(OC);
	}
}
