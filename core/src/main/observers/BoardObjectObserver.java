package observers;

import boardobjects.BoardObject;

public interface BoardObjectObserver {
	void actOnBoardObjectChange(BoardObject boardObject, boolean remove, boolean clickedOn);
}
