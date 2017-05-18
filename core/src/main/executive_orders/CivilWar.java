package executive_orders;

import buildings.BoardObject;
import models.BuildingModel;
import politics.parties.Party;

public class CivilWar implements ExecutiveOrder{
	BuildingModel BM;
	String killParty;
	private Party party;
	public CivilWar(BuildingModel BM, String killParty, Party party){
		this.BM  = BM;
		this.killParty = killParty;
		this.party= party;
	}
	
	@Override
	public void execute() {
		for(BoardObject BO : BM.getAllBoardObjects()){
			if(BO.getParty() != null && BO.getParty().equals(party)){
				BM.sellBoardObject(BO);
			}
		}
		
	}

	@Override
	public String getDescription() {
		return "Kills every "+ party.getName() +" tower and building. Rewards you with"
				+ " alot of political points.";
	}

	@Override
	public Party getParty() {
		return party;
	}

}
