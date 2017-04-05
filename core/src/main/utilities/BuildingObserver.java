package utilities;

import buildings.Building;

public interface BuildingObserver {
	void actOnBuildingChange(Building building);
}
