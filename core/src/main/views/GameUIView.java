package views;

import buildings.Building;
import buildings.WhiteHouse;
import observers.BuildingObserver;
import observers.WhiteHouseObserver;
import stages.NextWaveStage;
import stages.PoliticalMeterStage;
import stages.RightGameUIStage;
import stages.SelectedBuildingStage;
import stages.SuperpowerStage;
import stages.TopLeftGameUIStage;

public class GameUIView extends SimpleView implements WhiteHouseObserver, BuildingObserver {
	private RightGameUIStage HS;
	private PoliticalMeterStage PMS;
	private SelectedBuildingStage SBS;
	private TopLeftGameUIStage TL;
	private NextWaveStage NW;
	private SuperpowerStage SS;
	

	public GameUIView(PoliticalMeterStage PMS, RightGameUIStage HS, TopLeftGameUIStage TL,SelectedBuildingStage SBS, NextWaveStage NW, SuperpowerStage SS) {
		this.HS = HS;
		this.PMS = PMS;
		this.SBS = SBS;
		this.TL = TL;
		this.NW = NW;
		this.SS = SS;
		addToView(NW);
		addToView(TL);
		addToView(HS);
		addToView(PMS);
		addToView(SS);
	}

	@Override
	public void actOnWhiteHouseChange(WhiteHouse whitehouse) {
		PMS.updateParty(whitehouse.getPartyMap());
		TL.updateUI(Float.toString(whitehouse.getMoney()), Float.toString(whitehouse.getHealth()));
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
