package views;

import com.badlogic.gdx.scenes.scene2d.Stage;

import stages.GameUIStage;
import stages.PoliticalMeterStage;
import utilities.DrawablesCollector;
import utilities.PartyObserver;

public class GameUIView implements PartyObserver{
	GameUIStage HS;
	PoliticalMeterStage PMS;
	private DrawablesCollector SC = DrawablesCollector.getInstance();
	
	public GameUIView(PoliticalMeterStage PMS, GameUIStage HS){
		this.HS = HS;
		this.PMS = PMS;

		addToView(HS);
		addToView(PMS);
	}
	
	public void addToView(Stage stage) {
		SC.addStage(stage);
	}

	@Override
	public void actOnPartyVote(String party, int votes) {
		PMS.addParty(party, votes);
		
	}
	
	
}
