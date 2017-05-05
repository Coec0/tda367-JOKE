package observers;

import buildings.Building;

public interface BuildingObserver {
	void actOnBuildingChange(Building building, boolean remove, boolean clickedOn);
}
