package views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Touchable;

import boardobjects.BoardObject;
import boardobjects.WhiteHouse;
import boardobjects.towers.BOPrototypes;
import observers.BoardObjectObserver;
import observers.PrototypeObserver;
import observers.SuperpowerObserver;
import observers.WhiteHouseObserver;
import politics.parties.PartyFactory;
import stages.EndGamePopupStage;
import stages.NextWaveStage;
import stages.PoliticalMeterStage;
import stages.RightGameUIStage;
import stages.SelectedBoardObjectStage;
import stages.SuperpowerStage;
import stages.TopLeftGameUIStage;
import superpowers.Superpower;
import utilities.DrawablesCollector;

public class GameUIView extends SimpleView implements WhiteHouseObserver, BoardObjectObserver, PrototypeObserver, SuperpowerObserver {
	private RightGameUIStage HS;
	private PoliticalMeterStage PMS;
	private SelectedBoardObjectStage SBOS;
	private TopLeftGameUIStage TL;
	private NextWaveStage NW;
	private SuperpowerStage SS;
	private EndGamePopupStage EGP;
	private int nukeCost=0, minutemenCost=0, wallCost=0, towerBoosterCost=0;

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
		if(nukeCost> whitehouse.getParty(PartyFactory.Democrat(0)).getPoints()){
			SS.disableNuke(Touchable.disabled);
		} else {
			SS.disableNuke(Touchable.enabled);
		}
		
		if(minutemenCost> whitehouse.getParty(PartyFactory.Democrat(0)).getPoints()){
			SS.disableMinutemen(Touchable.disabled);
		} else {
			SS.disableMinutemen(Touchable.enabled);
		}
		
		if(wallCost> whitehouse.getParty(PartyFactory.Republican(0)).getPoints()){
			SS.disableWall(Touchable.disabled);
		} else {
			SS.disableWall(Touchable.enabled);
		}
		
		if(towerBoosterCost> whitehouse.getParty(PartyFactory.Republican(0)).getPoints()){
			SS.disableTowerBoost(Touchable.disabled);
		} else {
			SS.disableTowerBoost(Touchable.enabled);
		}
		
//		SS.updateSuperPowerButton(power, cost, disable);
		
	}

	@Override
	public void actOnBoardObjectChange(BoardObject BO, boolean remove, boolean clickedOn) {
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

	@Override
	public void actOnSuperPowerChange(Superpower superpower) {
		if(superpower.getName().equals("NUKE")){
			nukeCost = superpower.getSuperPowerCost();
			SS.updateNukeCost(superpower.getSuperPowerCost());
		} else if(superpower.getName().equals("TOWERBOOSTER")) {
			towerBoosterCost = superpower.getSuperPowerCost();
			SS.updateTowerBoostCost(superpower.getSuperPowerCost());
		} else if(superpower.getName().equals("WALL")) {
			wallCost = superpower.getSuperPowerCost();
			SS.updateWallCost(superpower.getSuperPowerCost());
		} else if(superpower.getName().equals("MINUTEMEN")) {
			minutemenCost = superpower.getSuperPowerCost();
			SS.updateMinuteMenCost(superpower.getSuperPowerCost());
		}
	}

}
