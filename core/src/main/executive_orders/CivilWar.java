package executive_orders;

import com.badlogic.gdx.utils.Array;

import buildings.BoardObject;
import models.BoardObjectModel;
import politics.parties.Party;

public class CivilWar implements ExecutiveOrder{
	private BoardObjectModel BOModel;
	private Party killParty;
	private Party killerParty;
	public CivilWar(BoardObjectModel BOModel, Party killerParty, Party killParty){
		this.BOModel  = BOModel;
		this.killParty = killParty;
		this.killerParty = killerParty;
	}
	
	@Override
	public void execute() {
		int points= 0;
		Array<BoardObject> gonnaSell = new Array<BoardObject>(false, BOModel.getAllBoardObjects().size);
		for(BoardObject BO : BOModel.getAllBoardObjects()){
			System.out.println("BoardObjects "+BOModel.getAllBoardObjects().size);
			if(BO.getParty() != null && BO.getParty().equals(killParty)){
				points += BO.getParty().getPoints();
				gonnaSell.add(BO);
				
			}
		}
		for(BoardObject BO : gonnaSell){
			BOModel.sellBoardObject(BO, false);
		}
		BOModel.getWhiteHouses().peek().voteParty(new Party(killerParty.getName(), 0, points));
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
