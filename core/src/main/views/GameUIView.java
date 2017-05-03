package views;

import com.badlogic.gdx.scenes.scene2d.Stage;

import buildings.Building;
import stages.GameUIStage;
import stages.PoliticalMeterStage;
import stages.SelectedBuildingStage;
import utilities.BuildingObserver;
import utilities.DrawablesCollector;
import utilities.PartyObserver;
import utilities.SpriteAdapter;

public class GameUIView implements PartyObserver, BuildingObserver {
	GameUIStage HS;
	PoliticalMeterStage PMS;
	SelectedBuildingStage SBS;
	private DrawablesCollector SC = DrawablesCollector.getInstance();

	public GameUIView(PoliticalMeterStage PMS, GameUIStage HS, SelectedBuildingStage SBS) {
		this.HS = HS;
		this.PMS = PMS;
		this.SBS = SBS;

		addToView(HS);
		addToView(PMS);
	}

	public void addToView(Stage stage) {
		SC.addStage(stage);
	}

	public void removeFromView(Stage stage) {
		SC.removeStage(stage);
	}

	@Override
	public void actOnPartyVote(String party, int votes) {
		PMS.addParty(party, votes);

	}

	@Override
	public void actOnBuildingChange(Building building, boolean remove, boolean clickedOn) {
		if(!remove && clickedOn){
			SBS.setBuilding(building);
			removeFromView(HS);
			addToView(SBS);
		} else if(remove && clickedOn){
			removeFromView(SBS);
			addToView(HS);
		}

	}

}
