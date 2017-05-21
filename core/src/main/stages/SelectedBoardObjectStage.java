package stages;


import buildings.BoardObject;
import buildings.Building;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import towers.Tower;
import utilities.DrawablesCollector;

/**
 * The Stage for showing additional information about a selected BoardObject.
 * @author Johan Svennungsson
 */
public class SelectedBoardObjectStage extends AbstractStage {

	private DrawablesCollector DC = DrawablesCollector.getInstance();

	private SelectedBuildingStage selectedBuildingStage;
	private SelectedTowerStage selectedTowerStage;

	static final float stageWidth = 200;

	protected SelectedBoardObjectStage() {}

	public SelectedBoardObjectStage(InputMultiplexer inputMultiplexer, ClickListener clickListener) {
		selectedBuildingStage = new SelectedBuildingStage(clickListener);
		selectedBuildingStage.setVisible(false);
		selectedTowerStage = new SelectedTowerStage(clickListener);
		selectedTowerStage.setVisible(false);
		inputMultiplexer.addProcessor(selectedBuildingStage);
		inputMultiplexer.addProcessor(selectedTowerStage);
	}

	public void setBoardObjectStage(BoardObject boardObject) {
		if (boardObject instanceof Tower) {
			DC.addStage(selectedTowerStage);
			selectedTowerStage.setTower((Tower) boardObject);
		} else {
			DC.removeStage(selectedTowerStage);
		}

		if (!(boardObject instanceof Tower)) {
			DC.addStage(selectedBuildingStage);
			selectedBuildingStage.setBuilding(boardObject);
		} else {
			DC.removeStage(selectedBuildingStage);
		}
	}

	public SelectedTowerStage getSelectedTowerStage() {
		return selectedTowerStage;
	}

	public SelectedBuildingStage getSelectedBuildingStage() {
		return selectedBuildingStage;
	}
}
