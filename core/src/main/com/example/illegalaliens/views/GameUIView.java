package com.example.illegalaliens.views;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.boardobjects.BoardObjectObserver;
import com.example.illegalaliens.models.boardobjects.WhiteHouse;
import com.example.illegalaliens.models.boardobjects.WhiteHouseObserver;
import com.example.illegalaliens.models.boardobjects.towers.BOPrototypes;
import com.example.illegalaliens.models.boardobjects.towers.PrototypeObserver;
import com.example.illegalaliens.models.enemies.waves.WavesObserver;
import com.example.illegalaliens.models.executive_orders.ExecutiveOrderObserver;
import com.example.illegalaliens.models.politics.parties.PartyFactory;
import com.example.illegalaliens.models.superpowers.Superpower;
import com.example.illegalaliens.models.superpowers.SuperpowerObserver;
import com.example.illegalaliens.utilities.DrawablesCollector;
import com.example.illegalaliens.views.stages.BottomLeftGameUIStage;
import com.example.illegalaliens.views.stages.EndGamePopupStage;
import com.example.illegalaliens.views.stages.NextWaveStage;
import com.example.illegalaliens.views.stages.PoliticalMeterStage;
import com.example.illegalaliens.views.stages.RightGameUIStage;
import com.example.illegalaliens.views.stages.SelectedBoardObjectStage;
import com.example.illegalaliens.views.stages.SuperpowerStage;

public class GameUIView extends SimpleView implements WhiteHouseObserver, BoardObjectObserver, PrototypeObserver, SuperpowerObserver, WavesObserver, ExecutiveOrderObserver {
	private RightGameUIStage HS;
	private PoliticalMeterStage PMS;
	private SelectedBoardObjectStage SBOS;
	private BottomLeftGameUIStage TL;
	private NextWaveStage NW;
	private SuperpowerStage SS;
	private EndGamePopupStage EGP;
	private int nukeCost=0, minutemenCost=0, wallCost=0, towerBoosterCost=0;
	private WhiteHouse whitehouse;
	private boolean waveFinished=true;

	public GameUIView(DrawablesCollector DC, PoliticalMeterStage PMS, RightGameUIStage HS, BottomLeftGameUIStage TL,
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
		this.whitehouse = whitehouse;
		if(whitehouse.isGameOver()){
			addToView(EGP);
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
		
		waveUpdate(waveFinished);
//		SS.updateSuperPowerButton(power, cost, disable);
		
	}
	
	private void waveUpdate(boolean waveFinished){
		if(!waveFinished){
			SS.disableWall(Touchable.disabled);
		} else {
			SS.disableMinutemen(Touchable.disabled);
			SS.disableNuke(Touchable.disabled);
			SS.disableTowerBoost(Touchable.disabled);
		}
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

	@Override
	public void actOnWavesChange(int wave, boolean finished) {
		System.out.println("StartNextWave");
		waveFinished = finished;
		NW.disableButton(!finished);
		updateSuperPowerButtons(whitehouse);
	
		
	}

	@Override
	public void actOnExecutiveOrdersChange(String EO, boolean onCD) {
		if(EO.equals("CW")){
			if(onCD){
				HS.disableCivilWar(Touchable.disabled);
			} else {
				HS.disableCivilWar(Touchable.enabled);
			}
		} else if(EO.equals("TC")){
			if(onCD){
				HS.disableTowerChanger(Touchable.disabled);
			} else {
				HS.disableTowerChanger(Touchable.enabled);
			}
		} else if(EO.equals("OB")){
			if(onCD){
				HS.disableOpenBorders(Touchable.disabled);
			} else {
				HS.disableOpenBorders(Touchable.enabled);
			}
		} else if(EO.equals("OB")){
			if(onCD){
				HS.disableOpenBorders(Touchable.disabled);
			} else {
				HS.disableOpenBorders(Touchable.enabled);
			}
		} else if(EO.equals("DW")){
			if(onCD){
				HS.disableDeclareWar(Touchable.disabled);
			} else {
				HS.disableDeclareWar(Touchable.enabled);
			}
		}
		
	}

}
