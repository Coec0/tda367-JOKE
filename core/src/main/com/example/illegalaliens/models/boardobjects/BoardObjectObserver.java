package com.example.illegalaliens.models.boardobjects;

public interface BoardObjectObserver {
	void actOnBoardObjectChange(BoardObject boardObject, boolean remove, boolean clickedOn);
}
