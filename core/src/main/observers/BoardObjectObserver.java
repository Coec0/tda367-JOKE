package observers;

import buildings.BoardObject;

public interface BoardObjectObserver {
	void actOnBoardObjectChange(BoardObject boardObject, boolean remove, boolean clickedOn);
}
