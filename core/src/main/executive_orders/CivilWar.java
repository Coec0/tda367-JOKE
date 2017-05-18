package executive_orders;

import com.badlogic.gdx.utils.Array;

import buildings.BoardObject;

public class CivilWar implements ExecutiveOrder{
	Array<BoardObject> boardobjects;
	String killParty;
	
	public CivilWar(Array<BoardObject> boardobjects, String killParty){
		this.boardobjects = boardobjects;
		this.killParty = killParty;
	}
	
	@Override
	public void execute() {
		for(BoardObject BO : boardobjects){
			//if(BO.get)
		}
		
	}

}
