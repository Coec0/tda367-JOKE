package boardobjects;

public interface BoardObjectObserver {
	void actOnBoardObjectChange(BoardObject boardObject, boolean remove, boolean clickedOn);
}
