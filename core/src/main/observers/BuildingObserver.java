package observers;

import buildings.BoardObject;

public interface BuildingObserver {
	void actOnBuildingChange(BoardObject boardObject, boolean remove, boolean clickedOn);
}
