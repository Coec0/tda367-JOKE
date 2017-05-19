package executive_orders;

import com.badlogic.gdx.utils.Array;

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
		Array<BoardObject> gonnaSell = new Array<BoardObject>(false, BM.getAllBoardObjects().size);
		for(BoardObject BO : BM.getAllBoardObjects()){
			System.out.println("BoardObjects "+BM.getAllBoardObjects().size);
			if(BO.getParty() != null && BO.getParty().equals(killParty)){
				points += BO.getParty().getPoints();
				gonnaSell.add(BO);
				
			}
		}
		for(BoardObject BO : gonnaSell){
			BM.sellBoardObject(BO, false);
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
