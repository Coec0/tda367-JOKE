package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import buildings.BoardObject;
import buildings.WhiteHouse;
import observers.BuildingObserver;
import observers.PrototypeObserver;
import observers.WhiteHouseObserver;
import politics.parties.PartyFactory;
import stages.EndGamePopupStage;
import stages.NextWaveStage;
import stages.PoliticalMeterStage;
import stages.RightGameUIStage;
import stages.SelectedBoardObjectStage;
import stages.SuperpowerStage;
import stages.TopLeftGameUIStage;
import towers.BOPrototypes;
import utilities.DrawablesCollector;

public class GameUIView extends SimpleView implements WhiteHouseObserver, BuildingObserver, PrototypeObserver {
	private RightGameUIStage HS;
	private PoliticalMeterStage PMS;
	private SelectedBoardObjectStage SBOS;
	private TopLeftGameUIStage TL;
	private NextWaveStage NW;
	private SuperpowerStage SS;
	private EndGamePopupStage EGP;
	

	public GameUIView(DrawablesCollector DC, PoliticalMeterStage PMS, RightGameUIStage HS, TopLeftGameUIStage TL,
					  SelectedBoardObjectStage SBOS, NextWaveStage NW, SuperpowerStage SS, EndGamePopupStage EGP) {
		super(DC);
		this.EGP = EGP;
		this.HS = HS;
		this.PMS = PMS;
		this.SBOS = SBOS;
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
		if(whitehouse.isGameOver()){
			addToView(EGP);
			Gdx.input.setInputProcessor((InputProcessor) EGP); //easy fix for disabling all other buttons
		}
		PMS.updatePartyMeter(whitehouse.getParliament());
		TL.updateUI(Float.toString(whitehouse.getMoney()), Float.toString(whitehouse.getHealth()));
		
		updateSuperPowerButtons(whitehouse);
	}

	private void updateSuperPowerButtons(WhiteHouse whitehouse) {
		if(SS.getPowerCost()> whitehouse.getParty(PartyFactory.Democrat(0)).getPoints()){
			SS.disableDemocrat(Touchable.disabled);
			System.out.println("DISBALED");
		} else {
			SS.disableDemocrat(Touchable.enabled);
		}
		if(SS.getPowerCost()> whitehouse.getParty(PartyFactory.Republican(0)).getPoints()){
			SS.disableRepublican(Touchable.disabled);
		} else {
			SS.disableRepublican(Touchable.enabled);
		}
		
//		SS.updateSuperPowerButton(power, cost, disable);
		
	}

	@Override
	public void actOnBuildingChange(BoardObject BO, boolean remove, boolean clickedOn) {
		if(!remove && clickedOn){
			SBOS.setBoardObjectStage(BO);
			removeFromView(HS);
			addToView(SBOS);
		} else if(remove){
			removeFromView(SBOS);
			removeFromView(SBOS.getSelectedBuildingStage());
			removeFromView(SBOS.getSelectedTowerStage());
			addToView(HS);
		} else if(!remove && !clickedOn){
			removeFromView(SBOS);
			removeFromView(SBOS.getSelectedBuildingStage());
			removeFromView(SBOS.getSelectedTowerStage());
			addToView(HS);
		}

	}

	@Override
	public void actOnPrototypeChange(BOPrototypes prototypes) {
		HS.updatePurchables(prototypes);
		
	}

}
