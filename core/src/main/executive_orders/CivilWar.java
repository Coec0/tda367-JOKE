package executive_orders;

import buildings.BoardObject;
import models.BuildingModel;

public class CivilWar implements ExecutiveOrder{
	BuildingModel BM;
	String killParty;
	
	public CivilWar(BuildingModel BM, String killParty){
		this.BM  = BM;
		this.killParty = killParty;
	}
	
	@Override
	public void execute() {
		for(BoardObject BO : BM.getAllBoardObjects()){
			if(BO.getParty() != null && BO.getParty().getName().equals(killParty)){
				BM.sellBoardObject(BO);
			}
		}
		
	}

}
