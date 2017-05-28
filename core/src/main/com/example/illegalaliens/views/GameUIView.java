package com.example.illegalaliens.views;

import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.example.illegalaliens.models.boardobjects.BoardObject;
import com.example.illegalaliens.models.boardobjects.BoardObjectObserver;
import com.example.illegalaliens.models.boardobjects.Superpower;
import com.example.illegalaliens.models.boardobjects.WhiteHouse;
import com.example.illegalaliens.models.boardobjects.WhiteHouseObserver;
import com.example.illegalaliens.models.boardobjects.towers.BOPrototypes;
import com.example.illegalaliens.models.boardobjects.towers.PrototypeObserver;
import com.example.illegalaliens.models.enemies.waves.WavesObserver;
import com.example.illegalaliens.models.executive_orders.ExecutiveOrderObserver;
import com.example.illegalaliens.models.politics.parties.PartyFactory;
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
	private RightGameUIStage RGUIS;
	private PoliticalMeterStage PMS;
	private SelectedBoardObjectStage SBOS;
	private BottomLeftGameUIStage TL;
	private NextWaveStage NW;
	private SuperpowerStage SS;
	private EndGamePopupStage EGP;
	private int nukeCost=0, minutemenCost=0, wallCost=0, towerBoosterCost=0;
	private final WhiteHouse whitehouse;
	private final BOPrototypes protos;
	private boolean waveFinished=true;

	public GameUIView(DrawablesCollector DC, PoliticalMeterStage PMS, RightGameUIStage HS, BottomLeftGameUIStage TL,
					  SelectedBoardObjectStage SBOS, NextWaveStage NW, SuperpowerStage SS, EndGamePopupStage EGP, WhiteHouse whitehouse, BOPrototypes protos) {
		super(DC);
		this.EGP = EGP;
		this.RGUIS = HS;
		this.PMS = PMS;
		this.SBOS = SBOS;
		this.TL = TL;
		this.NW = NW;
		this.SS = SS;
		this.whitehouse = whitehouse;
		this.protos = protos;
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
		}
		PMS.updatePartyMeter(whitehouse.getParliament());
		TL.updateUI(Integer.toString((int)whitehouse.getMoney()), Integer.toString((int)whitehouse.getHealth()));
		
		updateSuperPowerButtons(whitehouse);
		updateTowersButtons(whitehouse);
	}

	private void updateTowersButtons(WhiteHouse whitehouse) {
		RGUIS.disableSoldier(protos.getSoldier(0, 0).getCost()>=whitehouse.getMoney());
		RGUIS.disableRanger(protos.getRanger(0, 0).getCost()>=whitehouse.getMoney());
		RGUIS.disableRiotShield(protos.getRiotShield(0, 0).getCost()>=whitehouse.getMoney());
		RGUIS.disableSniper(protos.getSniper(0, 0).getCost()>=whitehouse.getMoney());
		RGUIS.disableTank(protos.getTank(0, 0).getCost()>=whitehouse.getMoney());
		RGUIS.disableNetGunner(protos.getNetGunner(0, 0).getCost()>=whitehouse.getMoney());
		
	}

	private void updateSuperPowerButtons(WhiteHouse whitehouse) {
		SS.disableNuke(nukeCost> whitehouse.getParty(PartyFactory.Democrat(0)).getPoints());
		SS.disableMinutemen(minutemenCost> whitehouse.getParty(PartyFactory.Democrat(0)).getPoints());
		SS.disableWall(wallCost> whitehouse.getParty(PartyFactory.Republican(0)).getPoints());
		SS.disableTowerBoost(towerBoosterCost> whitehouse.getParty(PartyFactory.Republican(0)).getPoints());

		waveUpdate(waveFinished);

	}
	
	private void waveUpdate(boolean waveFinished){
		if(!waveFinished){
			SS.disableWall(true);
		} else {
			SS.disableMinutemen(true);
			SS.disableNuke(true);
			SS.disableTowerBoost(true);
		}
	}
	
	@Override
	public void actOnBoardObjectChange(BoardObject BO, boolean remove, boolean clickedOn) {
		if(!remove && clickedOn){
			SBOS.setBoardObjectStage(BO);
			removeFromView(RGUIS);
			addToView(SBOS);
		} else if(remove){
			removeFromView(SBOS);
			removeFromView(SBOS.getSelectedBuildingStage());
			removeFromView(SBOS.getSelectedTowerStage());
			addToView(RGUIS);
		} else if(!remove && !clickedOn){
			removeFromView(SBOS);
			removeFromView(SBOS.getSelectedBuildingStage());
			removeFromView(SBOS.getSelectedTowerStage());
			addToView(RGUIS);
		}

	}

	@Override
	public void actOnPrototypeChange(BOPrototypes prototypes) {
		RGUIS.updatePurchables(prototypes);
		updateTowersButtons(whitehouse);
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
		waveFinished = finished;
		NW.disableButton(!finished);
		updateSuperPowerButtons(whitehouse);
	
		
	}

	@Override
	public void actOnExecutiveOrdersChange(String EO, boolean onCD) {
		if(EO.equals("CW")){
			RGUIS.disableCivilWar(onCD);
		} else if(EO.equals("TC")){
			RGUIS.disableTowerChanger(onCD);
		} else if(EO.equals("OB")){
			RGUIS.disableOpenBorders(onCD);
		} else if(EO.equals("OB")){
			RGUIS.disableOpenBorders(onCD);
		} else if(EO.equals("DW")){
			RGUIS.disableDeclareWar(onCD);
		}
		
	}

}
