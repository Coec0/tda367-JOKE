package models;

import cooldown.WavesCDHandler;
import cooldown.WavesCooldown;
import executive_orders.CivilWar;
import executive_orders.DeclareWar;
import executive_orders.OpenBorders;
import executive_orders.TowerChanger;
import politics.parties.Party;
import politics.parties.PartyFactory;
import towers.BOPrototypes;

public class ExecutiveOrdersModel implements WavesCooldown {

	private CivilWar CWR, CWD;
	private DeclareWar DW;
	private TowerChanger TC, OC;
	private OpenBorders OB;
	private Party republican, democrat;
	private WavesCDHandler wcd;
	private final String civilwarHASH="789ga789g27a8gd67";
	private final String towerchangerHASH="7h82189g27a8gd67";
	private final String declareHASH="926hts9g27a8gd67";
	private final String openbordersHASH="kih7ytrg27a8gd67";
	private boolean openBordersOnCooldown=false, civilOnCooldown=false, towerChangerOnCooldown=false, declareWarOnCooldown=false;

	public ExecutiveOrdersModel(BoardObjectModel BOModel, AlienModel AM, WavesCDHandler wcd, BOPrototypes prots){
		this.wcd = wcd;
		republican = PartyFactory.Republican(0);
		democrat = PartyFactory.Democrat(0);
		CWD = new CivilWar(BOModel, republican, democrat);
		CWR = new CivilWar(BOModel, democrat, republican);
		
		TC = new TowerChanger(BOModel.getWhiteHouses().peek(), prots, 0.5f, 0.5f , PartyFactory.Republican(100));
		OC = new TowerChanger(BOModel.getWhiteHouses().peek(), prots, 1.5f, 1.5f, PartyFactory.Democrat(100));
		
		DW = new DeclareWar(BOModel.getWhiteHouses().peek(), 30000, PartyFactory.Republican(30), prots);
		OB = new OpenBorders(AM, PartyFactory.Democrat(30));
	}
	
	
	private void activateCD(int rounds, String hash){
		wcd.startCooldown(this, rounds, hash);
	}
	
	private void civilWarCD(){
		activateCD(5,civilwarHASH);
		civilOnCooldown = true;
	}
	
	private void towerchangerCD(){
		activateCD(5,towerchangerHASH);
		towerChangerOnCooldown = true;
	}
	
	public void openBorders(){
		if(!openBordersOnCooldown){
			OB.execute();
			activateCD(1, openbordersHASH);
			openBordersOnCooldown = true;
		}
	}
	
	public void declareWar(){
		if(!declareWarOnCooldown){
			DW.execute();
			wcd.startCooldown(DW);
			activateCD(7, declareHASH);
			declareWarOnCooldown =true;
		}
	}
	
	
	public void civilWarAgainstRepublicans(){
		if(!civilOnCooldown){
			CWR.execute();
			civilWarCD();
		}
	}
	
	public void civilWarAgainstDemocrats(){
		if(!civilOnCooldown){
			CWD.execute();
			civilWarCD();
		}
	}
	
	public void taxCut(){
		if(!towerChangerOnCooldown){
			TC.execute();
			wcd.startCooldown(TC);
			towerchangerCD();
		}
	}
	
	public void obamaCare(){
		if(!towerChangerOnCooldown){
			OC.execute();
			wcd.startCooldown(OC);
			towerchangerCD();
		}
	}



	@Override
	public int cdTurns() {
		return 0;
	}



	@Override
	public void afterCD(String hash) {
		if(hash.equals(civilwarHASH)){
			civilOnCooldown = false;
		} else if(hash.equals(towerchangerHASH)){
			towerChangerOnCooldown = false;
		} else if(hash.equals(declareHASH)){
			declareWarOnCooldown = false;
		} else if(hash.equals(openbordersHASH)){
			openBordersOnCooldown = false;
		}
			
		
	}
}
