package views;

import com.badlogic.gdx.scenes.scene2d.Stage;

import buildings.Building;
import buildings.WhiteHouse;
import observers.BuildingObserver;
import observers.WhiteHouseObserver;
import stages.GameUIStage;
import stages.PoliticalMeterStage;
import stages.SelectedBuildingStage;
import utilities.DrawablesCollector;

public class GameUIView implements WhiteHouseObserver, BuildingObserver {
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
	public void actOnWhiteHouseChange(WhiteHouse whitehouse) {
		PMS.updateParty(whitehouse.getPartyMap());
	}

	@Override
	public void actOnBuildingChange(Building building, boolean remove, boolean clickedOn) {
		if(!remove && clickedOn){
			SBS.setBuilding(building);
			removeFromView(HS);
			addToView(SBS);
		} else if(remove){
			removeFromView(SBS);
			addToView(HS);
		} else if(!remove && !clickedOn){
			removeFromView(SBS);
			addToView(HS);
		}

	}



}
