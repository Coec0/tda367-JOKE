package com.example.illegalaliens.models;

import com.badlogic.gdx.utils.Array;
import com.example.illegalaliens.models.boardobjects.WhiteHouse;
import com.example.illegalaliens.models.boardobjects.towers.Soldier;
import com.example.illegalaliens.models.boardobjects.towers.Tower;
import com.example.illegalaliens.models.boardobjects.towers.TowerUpgrader;
import com.example.illegalaliens.utilities.Radar;
import com.example.illegalaliens.utilities.cooldown.CooldownHandler;
import com.example.illegalaliens.utilities.cooldown.WavesCDHandler;
import com.example.illegalaliens.utilities.path.RoadManager;
import com.example.illegalaliens.utilities.path.map.Map;
import com.example.illegalaliens.utilities.path.map.MapNode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test for BoardObjectModel
 * @author Johan Svennungsson
 */
public class BoardObjectModelTest {

    private TowerUpgrader towerUpgrader;
    private BoardObjectModel boardObjectModel;
    private Tower soldier;
    private WhiteHouse whiteHouse;

    @Before
    public void setUp() throws Exception {
        Map map = new Map("AlphaMap");
        Array<MapNode> startingNodes = map.getStartingNodes();
        Array<MapNode> allNodes = map.getMapNodes();
        MapNode endNode = allNodes.peek();
        CooldownHandler cooldownHandler = new CooldownHandler();
        Radar radar = new Radar();
        RoadManager manager = new RoadManager(allNodes, endNode, startingNodes, radar);
        WavesCDHandler wavesCDHandler = new WavesCDHandler();
        AlienModel alienModel = new AlienModel(manager, map.getStartingNodes(), wavesCDHandler);

        towerUpgrader = new TowerUpgrader();
        boardObjectModel = new BoardObjectModel(alienModel.getAllEnemies(), cooldownHandler, radar, manager);
        soldier = new Soldier(2000,2000);
        whiteHouse = new WhiteHouse("WhiteHouse", (int) endNode.getPos().getX(), (int) endNode.getPos().getY(),50, 1000,null);

        boardObjectModel.addWhiteHouse(whiteHouse);
    }

    @Test
    public void isFreeSpace() throws Exception {
        boardObjectModel.addBoardObject(soldier);

        assertEquals(boardObjectModel.isFreeSpace(2000, 2000, soldier), false);
        assertEquals(boardObjectModel.isFreeSpace(2036, 2036, soldier), true);
    }

    @Test
    public void purchaseHighlightedObject() throws Exception {
        boardObjectModel.clickedBuilding(soldier);
        boardObjectModel.purchaseHighlightedObject((int) soldier.getPos().getX(), (int) soldier.getPos().getY());
        assertEquals(boardObjectModel.getAllBoardObjects().contains(soldier, false), true);
    }

    @Test
    public void getHighlighted() throws Exception {
        assertEquals(boardObjectModel.getHighlighted(), null);
        boardObjectModel.clickedBuilding(soldier);
        assertEquals(boardObjectModel.getHighlighted(), soldier);
        boardObjectModel.deselectHighlighted();
        assertEquals(boardObjectModel.getHighlighted(), null);
    }

    @Test
    public void clickedBuilding() throws Exception {
        boardObjectModel.clickedBuilding(soldier);
        assertEquals(boardObjectModel.getHighlighted(), soldier);
    }

    @Test
    public void deselectHighlighted() throws Exception {
        boardObjectModel.clickedBuilding(soldier);
        boardObjectModel.deselectHighlighted();
        assertEquals(boardObjectModel.getHighlighted(), null);
    }

    @Test
    public void addBoardObject() throws Exception {
        boardObjectModel.addBoardObject(soldier);
        assertEquals(whiteHouse.getMoney(), 1000 - soldier.getCost(), 0);
        assertEquals(boardObjectModel.getAllBoardObjects().contains(soldier, false), true);
        assertEquals(soldier.isActive(), true);
    }

    @Test
    public void sellBoardObject() throws Exception {
        boardObjectModel.sellBoardObject(soldier, true);
        assertEquals(whiteHouse.getMoney(), 1000 + soldier.getValue(), 0);
        assertEquals(boardObjectModel.getAllBoardObjects().contains(soldier, false), false);
    }

    @Test
    public void upgradeTowerDamage() throws Exception {
        assertEquals(soldier.getDamage(), 20, 0);
        boardObjectModel.upgradeTowerDamage(soldier);
        assertEquals(soldier.getDamage(), 26, 0);
        assertEquals(whiteHouse.getMoney(), 1000 - towerUpgrader.getDamageUpgradeCost(soldier), 0);
    }

    @Test
    public void upgradeTowerCooldown() throws Exception {
        assertEquals(soldier.getCooldownObject().getCooldownTime(), 25, 0);
        boardObjectModel.upgradeTowerCooldown(soldier);
        assertEquals(soldier.getCooldownObject().getCooldownTime(), 22.5, 0);
        assertEquals(whiteHouse.getMoney(), 1000 - towerUpgrader.getCooldownUpgradeCost(soldier), 0);
    }

    @Test
    public void upgradeTowerRadius() throws Exception {
        assertEquals(soldier.getRadius(), 230, 0);
        boardObjectModel.upgradeTowerRadius(soldier);
        assertEquals(soldier.getRadius(), 299, 0);
        assertEquals(whiteHouse.getMoney(), 1000 - towerUpgrader.getRadiusUpgradeCost(soldier), 0);
    }
}