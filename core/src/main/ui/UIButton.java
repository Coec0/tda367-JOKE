package ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.badlogic.gdx.utils.Array;

public class UIButton {

	public UIButton() {

	}

	
	private Array<ActionListener> aListeners = new Array<ActionListener>(false, 10);
	
	public void addActionListener(ActionListener l) {
		aListeners.add(l);
	}

	public void removeActionListener(ActionListener l) {
		aListeners.removeValue(l, false);
	}

	private void notifyListeners(ActionEvent e) {
		for (ActionListener l : aListeners)
			l.actionPerformed(e);
	}

}
