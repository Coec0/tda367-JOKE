package executive_orders;

import buildings.BoardObject;
import models.BuildingModel;
import politics.parties.Party;

public class CivilWar implements ExecutiveOrder{
	private BuildingModel BM;
	private Party killParty;
	private Party killerParty;
	public CivilWar(BuildingModel BM, Party killerParty, Party killParty){
		this.BM  = BM;
		this.killParty = killParty;
		this.killerParty = killerParty;
	}
	
	@Override
	public void execute() {
		int points= 0;
		for(BoardObject BO : BM.getAllBoardObjects()){
			if(BO.getParty() != null && BO.getParty().equals(killParty)){
				points += BO.getParty().getPoints();
				BM.sellBoardObject(BO, false);
			}
		}
		BM.getWhiteHouses().peek().voteParty(new Party(killerParty.getName(), 0, points));
	}

	@Override
	public String getDescription() {
		return "Kills every "+ killParty.getName() +" tower and building. Rewards you with"
				+ " alot of political points.";
	}

	@Override
	public Party getParty() {
		return killerParty;
	}

}
